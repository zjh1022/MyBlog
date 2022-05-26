package com.zjh.myblog.service;

import com.zjh.myblog.entity.Comment;
import com.zjh.myblog.mapper.CommentMapper;
import com.zjh.myblog.utlis.*;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-05 14:27
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 获取评论列表
     * @param comment
     * @return
     */
    public List<Comment> selectCommentList(Comment comment) {
        List<Comment> commentList = commentMapper.selectCommentList(comment);
        if (!SecurityUtils.isAdmin()) {
            commentList.forEach(e->{
                if (StringUtils.isNotEmpty(e.getQqNum())) {
                    e.setQqNum(e.getQqNum().replaceAll("[1-9][0-9]{4,}","*"));
                }
                if (StringUtils.isNotEmpty(e.getEmail())) {
                    e.setEmail(e.getEmail().replaceAll("[1-9][0-9]{4,}", "*"));
                }
            });
        }
        return commentList;
    }

    /**
     *根据id获取comment
     * @param id
     * @return
     */
    public Comment selectCommentById(Long id) {
        return commentMapper.selectCommentById(id);
    }

    /**
     *  新增评论
     * @param comment
     * @return
     */
    public int insertComment(Comment comment) {
        comment.setAdminReply(SecurityUtils.isAdmin());
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        comment.setOs(userAgent.getOperatingSystem().getName());
        comment.setBrowser(userAgent.getBrowser().getName());
        comment.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        comment.setLocation(AddressUtils.getCityInfoByIp(comment.getIp()));
        return commentMapper.insertComment(comment);
    }

    
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    
    public int deleteCommentByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return commentMapper.deleteCommentById(ConvertUtils.toLongArray(ids), username);
    }

    /**
     * 评论点赞
     * @param id
     * @return
     */
    public int incrementCommentGood(Long id) {
        return commentMapper.incrementCommentGood(id);
    }

    /**
     * 评论踩
     * @param id
     * @return
     */
    public int incrementCommentBad(Long id) {
        return commentMapper.incrementCommentBad(id);
    }

    /**
     * 根据PageId获取所有的评论
     * @param id
     * @return
     */
    public List<Comment> selectCommentListByPageId(Long id) {
        //查询获取所有的comment
        List<Comment> commentList = commentMapper.selectCommentListByPageId(id);
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

}
