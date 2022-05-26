package com.zjh.myblog.mapper;

import com.zjh.myblog.entity.TagMapping;
import org.apache.ibatis.annotations.Mapper;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
@Mapper
public interface TagMappingMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByBlogId(Long id);

    int insert(TagMapping record);

    int insertSelective(TagMapping record);

    TagMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TagMapping record);

    int updateByPrimaryKey(TagMapping record);
}