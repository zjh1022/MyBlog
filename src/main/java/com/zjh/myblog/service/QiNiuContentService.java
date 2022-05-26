package com.zjh.myblog.service;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.zjh.myblog.entity.QiNiuContent;
import com.zjh.myblog.execption.CustomException;
import com.zjh.myblog.mapper.QiNiuContentMapper;
import com.zjh.myblog.utlis.*;
import com.zjh.myblog.utlis.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
@description    
@auther zhengjianghai 
    
@create 2022-03-26 14:40

*/
@Service
@Slf4j
public class QiNiuContentService{

    @Resource
    private QiNiuContentMapper qiNiuContentMapper;



    private static final String accessKey="你的accessKey";

    private static final String secretKey="你的secretKey";

    private static final String bucket="你的bucket";

    private static final String host="你创建的域名地址";


    /**
     * 上传到七牛云
     * @param file
     * @return
     */
    public QiNiuContent upload(MultipartFile file) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuUtils.getRegion("华南"));
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        //生成上传文件Token
        String upToken = auth.uploadToken(bucket);
        QiNiuContent qiNiuContent = new QiNiuContent();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String key = FileUtils.getFileNameNoExtension(file.getOriginalFilename()) + df.format(new Date()) + "." + FileUtils.getExtensionName(file.getOriginalFilename());
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解析
            DefaultPutRet defaultPutRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            //将结果存入数据库
            qiNiuContent.setSuffix(FileUtils.getExtensionName(defaultPutRet.key));
            qiNiuContent.setBucket(bucket);
            qiNiuContent.setType("公开");
            qiNiuContent.setName(FileUtils.getFileNameNoExtension(defaultPutRet.key));
            qiNiuContent.setUrl("http://" + host+ "/" + defaultPutRet.key);
            qiNiuContent.setSize(FileUtils.getSizeString(Integer.parseInt(file.getSize() + "")));
            qiNiuContentMapper.insertContent(qiNiuContent);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return qiNiuContent;
    }

    /**
     * 刷新七牛云数据
      * @return
     */
    @Transactional
    public int synchronize() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(QiNiuUtils.getRegion("华南"));
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
        int count = 0;
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            QiNiuContent qiNiuContent;
            //删除所有数据
            qiNiuContentMapper.clearContent();
            FileInfo[] items = fileListIterator.next();
            if (Objects.nonNull(items)) {
                String username = SecurityUtils.getUsername();
                for (FileInfo item : items) {
                    qiNiuContent = new QiNiuContent();
                    qiNiuContent.setSize(FileUtils.getSizeString(Integer.parseInt(item.fsize + "")));
                    qiNiuContent.setSuffix(FileUtils.getExtensionName(item.key));
                    qiNiuContent.setName(FileUtils.getFileNameNoExtension(item.key));
                    qiNiuContent.setType("公开");
                    qiNiuContent.setBucket(bucket);
                    qiNiuContent.setUrl("http://" + host+ "/" + item.key);
                    qiNiuContent.setCreateTime(convertUnixTime(item.putTime + ""));
                    count+=qiNiuContentMapper.insertContent(qiNiuContent);
                }

            }
        }
        return count;
    }

    private Date convertUnixTime(String time) {
        if (StringUtils.isEmpty(time)) {
            return new Date();
        }
        //去掉后七位
        String realTimeStr = time.substring(0, time.length() - 7);
        try {
            Long createTime = Long.valueOf(realTimeStr);
            return new Date(createTime);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Date();
        }
    }

    
    public int deleteQiNiuContent(String ids) {
        Long[] idArray = ConvertUtils.toLongArray(ids);
        //查询
        List<QiNiuContent> qiNiuContentList = qiNiuContentMapper.selectContentByIds(idArray);

        for (QiNiuContent qiNiuContent : qiNiuContentList) {
            if (Objects.isNull(qiNiuContent) || StringUtils.isEmpty(qiNiuContent.getName())) {
                throw new CustomException("数据异常");
            }
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(QiNiuUtils.getRegion("华南"));
            Auth auth = Auth.create(accessKey, secretKey);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            try {
                bucketManager.delete(qiNiuContent.getBucket(), qiNiuContent.getName());
            } catch (QiniuException e) {
                log.error("删除七牛云图片出错,{},", e.getMessage(), e);
                //出错后删除本地数据库文件
            }
        }
        return qiNiuContentMapper.deleteContentByIds(idArray);
    }

    
    public String getDownloadUrl(Long id) {

        QiNiuContent qiNiuContent = qiNiuContentMapper.selectContentById(id);
            Auth auth = Auth.create(accessKey, secretKey);
            // 1小时，可以自定义链接过期时间
            long expireInSeconds = 3600;
            return auth.privateDownloadUrl(qiNiuContent.getUrl(), expireInSeconds);
    }

    
    public List<QiNiuContent> selectContentList(QiNiuContent qiNiuContent) {
        return qiNiuContentMapper.selectContentList(qiNiuContent);
    }
}
