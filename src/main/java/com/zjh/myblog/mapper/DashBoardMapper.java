package com.zjh.myblog.mapper;

import com.zjh.myblog.entity.LoginLog;
import com.zjh.myblog.entity.VisitLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-07 14:56
 */
@Mapper
public interface DashBoardMapper {

    /**
     * 获取访问数量
     *
     * @return 访问数量
     */
    Long getVisitorCount();

    /**
     * 获取博客数量
     *
     * @return 博客数量
     */
    Long getBlogCount();

    /**
     * 通过 createTime 获取访问日志计数
     *
     * @return count
     */
    Long getVisitorCountByCreateTime(@Param("date") String date);

    Long getCategoryCountByCreateTime(@Param("date") String date);


    Long getCommentCountByCreateTime(@Param("date") String date);


    /**
     * get blog count by createTime
     *
     * @param day current day string. eg:2019-08-08
     * @return count
     */
    Long getBlogCountByCreateTime(String day);

    List<Map<String, Long>> getSpiderData();

    /**
     * 获取visitLog
     *
     * @return visitLog
     */
    List<VisitLog> getVisitLog();

    /**
     * 根据Id获取blog的title
     *
     * @param pageId blog的id
     * @return blog的title
     */
    String getBlogNameByPageId(Long pageId);

    /**
     * 获取登录日志
     *
     * @return 登录日志
     */
    List<LoginLog> getLoginLogList();

    /**
     * 获取分类数量
     * @return
     */
    Long getCategoryCount();

    /**
     * 获取用户数量
     * @return
     */
    Long getUserCount();


    Long getCommentCount();

}
