package com.zjh.myblog.service;

import com.zjh.myblog.config.BlogConfig;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.LocalStorage;
import com.zjh.myblog.execption.CustomException;
import com.zjh.myblog.mapper.LocalStorageMapper;
import com.zjh.myblog.utlis.SecurityUtils;
import com.zjh.myblog.utlis.StringUtils;
import com.zjh.myblog.utlis.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @description 文件存储
 * @auther zhengjianghai
 * @create 2022-02-05 13:51
 */
@Service
public class LocalStorageService extends BaseController {

    @Autowired
    private LocalStorageMapper localStorageMapper;

    /**
     * 获取本地存储信息
     * @param localStorage
     * @return
     */
    public List<LocalStorage> selectLocalStorageList(LocalStorage localStorage) {
        return localStorageMapper.selectLocalStorageList(localStorage);
    }

    /**
     * 上传文件保存到数据库
     * @param name
     * @param multipartFile
     * @return 受影响的行数
     */
    public int upload(String name, MultipartFile multipartFile) {
        //检查大小
        //获取后缀
        String suffix = FileUtils.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtils.getFileType(suffix);
        File file = FileUtils.upload(multipartFile, BlogConfig.getProfile() + type + File.separator);
        if (Objects.isNull(file)) {
            throw new CustomException("上传失败");
        }
        //防止异常出错
        try {
            name = StringUtils.isBlank(name) ? FileUtils.getFileNameNoExtension(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(file.getName(), name, suffix, file.getPath(), type, FileUtils.getSizeString(multipartFile.getSize()));
            localStorage.setCreateBy(SecurityUtils.getUsername());
            return localStorageMapper.insertLocalStorage(localStorage);
        } catch (Exception e) {
            FileUtils.del(file);
            throw e;
        }
    }

    /**
     * 上传文件保存到数据库
     * @param multipartFile
     * @return 受影响的行数
     */
    public String upload1(MultipartFile multipartFile) {
        //检查大小
        //获取后缀
        String suffix = FileUtils.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtils.getFileType(suffix);
        File file = FileUtils.upload(multipartFile, BlogConfig.getProfile() + type + File.separator);
        if (Objects.isNull(file)) {
            throw new CustomException("上传失败");
        }
        //防止异常出错
        try {
            LocalStorage localStorage = new LocalStorage(file.getName(), null, suffix, file.getPath(), type, FileUtils.getSizeString(multipartFile.getSize()));
            localStorage.setCreateBy(SecurityUtils.getUsername());
            localStorageMapper.insertLocalStorage(localStorage);
            return file.getName();
        } catch (Exception e) {
            FileUtils.del(file);
            throw e;
        }
    }

    /**
     * 更改数据库中文件信息
     * @param localStorage
     * @return 受影响的行数
     */
    public int updateLocalStorage(LocalStorage localStorage) {
        localStorage.setUpdateBy(SecurityUtils.getUsername());
        return localStorageMapper.updateLocalStorage(localStorage);
    }

    /**
     * 删除本地存储信息
     *
     * @param id id
     * @return 受影响的行数
     */
    public int deleteLocalStorage(Long id) {
        String username = SecurityUtils.getUsername();
        LocalStorage localStorage = localStorageMapper.selectLocalStorageById(id);
        if (Objects.isNull(localStorage)) {
            throw new CustomException("文件不存在");
        }
        //删除文件
        String path = localStorage.getPath();
        FileUtils.del(path);
        return localStorageMapper.deleteLocalStorageById(id, username);
    }

}
