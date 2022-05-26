package com.zjh.myblog.service;

import com.zjh.myblog.constant.CacheConstants;
import com.zjh.myblog.entity.*;
import com.zjh.myblog.entity.dto.ReplayEmail;
import com.zjh.myblog.entity.vo.BlogQuery;
import com.zjh.myblog.mapper.FrontMapper;
import com.zjh.myblog.utlis.AddressUtils;
import com.zjh.myblog.utlis.IpUtils;
import com.zjh.myblog.utlis.SecurityUtils;
import com.zjh.myblog.utlis.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther zhengjianghai
 * @create 2022-01-26 9:42
 */
@Service
public class FrontService {

    @Autowired
    private FrontMapper frontMapper;



    public List<Link> selectLinkList() {
        return frontMapper.selectLinkList();
    }

    
    public int insertLink(Link link) {
        link.setStatus(false);
        link.setDisplay(false);
        return frontMapper.insertLink(link);
    }


    public List<Category> selectCategoryList() {
        return frontMapper.selectCategoryList();
    }

    
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT_SUPPORT_BLOG_LIST)
    public List<Blog> selectSupportBlogList() {
        return frontMapper.selectSupportBlogList();
    }

    
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT_HOT_BLOG_LIST)
    public List<Blog> selectHotBlogList() {
        return frontMapper.selectHotBlogList();
    }

    
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT_TAG_LIST)
    public List<Tag> selectTagList() {
        return frontMapper.selectTagList();
    }

    
//    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT_CAROUSEL_LIST)
    public List<Carousel> selectCarouselList() {
        return frontMapper.selectCarouselList();
    }

    
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT_NOTICE_LIST)
    public List<Notice> selectNoticeList() {
        return frontMapper.selectNoticeList();
    }

    
    public int insertComment(Comment comment) {
        comment.setAdminReply(SecurityUtils.isAdmin());
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        comment.setOs(userAgent.getOperatingSystem().getName());
        comment.setBrowser(userAgent.getBrowser().getName());
        comment.setDisplay(true);
        comment.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        comment.setLocation(AddressUtils.getCityInfoByIp(comment.getIp()));
        if (comment.getParentId() != null) {
            Comment tempComment = frontMapper.selectCommentById(comment.getParentId());
            String title = frontMapper.selectBlogTitleById(comment.getPageId());
            if (tempComment.getReply()) {
                ReplayEmail replayEmail = new ReplayEmail();
                replayEmail.setCreateTime(tempComment.getCreateTime());
                replayEmail.setOriginContent(tempComment.getHtmlContent());
                replayEmail.setReplyContent(comment.getHtmlContent());
                replayEmail.setUrl(comment.getUrl());
                replayEmail.setTitle(title);
            }
        }
        return frontMapper.insertComment(comment);
    }


    
    public List<Comment> selectCommentListByPageId(Long id) {
        //查询获取所有的comment
        List<Comment> commentList = frontMapper.selectCommentListByPageId(id);
        List<Comment> result = commentList.stream().filter(e -> e.getParentId() == null).collect(Collectors.toList());
        //CommentId和NickName的映射Map
        Map<Long, String> commentIdAndNickNameMap = commentList.stream().collect(Collectors.toMap(Comment::getId, Comment::getNickName));
        for (Comment comment : result) {
            Long commentId = comment.getId();
            comment.setSubCommentList(commentList.stream().filter(e -> commentId.equals(e.getParentId())).collect(Collectors.toList()));
            //设置replyNickName
            if (ObjectUtils.isNotEmpty(comment.getSubCommentList())) {
                for (Comment temp : comment.getSubCommentList()) {
                    if (temp.getReplyId() != null) {
                        temp.setReplyNickName(commentIdAndNickNameMap.get(temp.getReplyId()));
                    }
                }
            }
        }
        return result;
    }

    
    public int incrementCommentGood(Long id) {
        return frontMapper.incrementCommentGood(id);
    }

    
    public int incrementCommentBad(Long id) {
        return frontMapper.incrementCommentBad(id);
    }

    
    public int incrementBlogLike(Long id) {
        return frontMapper.incrementBlogLike(id);
    }

    
//    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT_BLOG_ITEM, key = "'BlogId:' +#id")
    public Blog selectBlogDetailById(Long id) {
        Blog blog = frontMapper.selectBlogDetailById(id);
        //get all comment
        blog.setCommentList(selectCommentListByPageId(id));
        return blog;
    }

    
    public List<Blog> selectBlogList(BlogQuery blogQuery) {
        List<Blog> blogList = frontMapper.selectBlogList(blogQuery);
        for (Blog blog : blogList) {
            blog.setTagList(frontMapper.selectTagListByTypeAndId(blog.getId()));
        }
        return blogList;
    }

    
    public int incrementLinkClick(Integer id) {
        return frontMapper.incrementLinkClick(id);
    }

    
    public int incrementBlogClick(Long id) {
        return frontMapper.incrementBlogClick(id);
    }

    
    public String selectAbout() {
        Config config = frontMapper.selectAbout();
        return config.getConfigValue();
    }

    
    public List<Blog> selectBlogArchive(BlogQuery blogQuery) {
        List<Blog> blogList = frontMapper.selectBlogList(blogQuery);
        for (Blog blog : blogList) {
            blog.setTagList(frontMapper.selectTagListByTypeAndId(blog.getId()));
        }
        return blogList;
    }

    
    @Cacheable(value = CacheConstants.CACHE_NAME_FRONT_SUPPORT_LINK_LIST)
    public List<Link> selectSupportLinkList() {
        return frontMapper.selectSupportLinkList();
    }
}
