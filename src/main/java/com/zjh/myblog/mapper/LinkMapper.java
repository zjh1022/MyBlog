package com.zjh.myblog.mapper;

import com.zjh.myblog.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
@Mapper
public interface LinkMapper {
    /**
     * 查询友链
     *
     * @param id 友链ID
     * @return 友链
     */
    Link selectLinkById(Long id);

    /**
     * 查询友链列表
     *
     * @param link 友链
     * @return 友链集合
     */
    List<Link> selectLinkList(Link link);

    /**
     * 新增友链
     *
     * @param link 友链
     * @return 结果
     */
    int insertLink(Link link);

    /**
     * 修改友链
     *
     * @param link 友链
     * @return 结果
     */
    int updateLink(Link link);

    /**
     * 删除友链
     *
     * @param id 友链ID
     * @return 结果
     */
    int deleteLinkById(@Param("id") Long id, @Param("username") String username);

    /**
     * 批量删除友链
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteLinkByIds(@Param("ids") Long[] ids, @Param("username") String username);
}