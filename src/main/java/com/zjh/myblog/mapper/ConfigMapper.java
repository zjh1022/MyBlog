package com.zjh.myblog.mapper;

import com.zjh.myblog.entity.Config;
import org.apache.ibatis.annotations.Mapper;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
@Mapper
public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}