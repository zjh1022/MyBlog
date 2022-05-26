package com.zjh.myblog.mapper;


import com.zjh.myblog.entity.*;
import com.zjh.myblog.entity.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: FrontMapper
 * @description:
 */
@Mapper
public interface FrontMapper {

    /**
     * 获取所有链接
     * @return
     */
    List<Link> selectLinkList();

    int insertLink(Link link);



    List<Category> selectCategoryList();


    List<Blog> selectSupportBlogList();


    @Deprecated
    List<Blog> selectHotBlogList();


    List<Tag> selectTagList();


    List<Carousel> selectCarouselList();


    List<Notice> selectNoticeList();


    int insertComment(Comment comment);


    List<Comment> selectCommentListByPageId(Long id);

    int incrementCommentGood(Long id);

    int incrementCommentBad(Long id);

    int incrementBlogLike(Long id);


    Blog selectBlogDetailById(Long id);


    int incrementBlogClick(Long id);

    List<Blog> selectBlogList(BlogQuery blogQuery);

    List<Tag> selectTagListByTypeAndId(@Param("id") Long id);

    int incrementLinkClick(Integer id);

    Config selectAbout();

    Comment selectCommentById(Long id);

    List<Link> selectSupportLinkList();

    String selectBlogTitleById(Long id);
}
