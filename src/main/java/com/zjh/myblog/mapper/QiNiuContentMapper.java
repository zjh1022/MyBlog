package com.zjh.myblog.mapper;

import com.zjh.myblog.entity.QiNiuContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
@description    
@auther zhengjianghai 
    
@create 2022-03-26 14:40

*/
@Mapper
public interface QiNiuContentMapper {
    /**
     * 获取七牛云文件List
     *
     * @param qiNiuContent 查询条件
     * @return list
     */
    List<QiNiuContent> selectContentList(QiNiuContent qiNiuContent);

    /**
     * 根据Id查询文件
     *
     * @param id id
     * @return 文件信息
     */
    QiNiuContent selectContentById(Long id);

    /**
     * 根据Ids获取Content List
     *
     * @param ids Long
     * @return List
     */
    List<QiNiuContent> selectContentByIds(@Param("ids") Long[] ids);

    /**
     * 根据Id删除文件
     *
     * @param ids id
     * @return 受影响的行数
     */
    int deleteContentByIds(@Param("ids") Long[] ids);

    /**
     * 插入数据
     *
     * @param qiNiuContent 数据实体
     * @return 受影响的行数
     */
    int insertContent(QiNiuContent qiNiuContent);

    /**
     * 清空表中数据
     */
    void clearContent();
}