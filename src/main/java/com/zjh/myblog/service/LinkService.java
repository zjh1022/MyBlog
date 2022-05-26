package com.zjh.myblog.service;

import com.zjh.myblog.entity.Link;
import com.zjh.myblog.execption.CustomException;
import com.zjh.myblog.mapper.LinkMapper;
import com.zjh.myblog.utlis.ConvertUtils;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @description 友链管理
 * @auther zhengjianghai
 * @create 2022-01-27 15:57
 */
@Service
public class LinkService {

    @Autowired
    private LinkMapper linkMapper;

    
    public Link selectLinkById(Long id) {
        return linkMapper.selectLinkById(id);
    }

    
    public List<Link> selectLinkList(Link link) {
        return linkMapper.selectLinkList(link);
    }

    /**
     * 新增友链
     * @param link
     * @return
     */
    public int insertLink(Link link) {
        return linkMapper.insertLink(link);
    }

    
    public int updateLink(Link link) {
        return linkMapper.updateLink(link);
    }

    
    public int deleteLinkByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return linkMapper.deleteLinkByIds(ConvertUtils.toLongArray(ids), username);
    }

    
    public int deleteLinkById(Long id) {
        String username = SecurityUtils.getUsername();
        return linkMapper.deleteLinkById(id, username);
    }

    /**
     * 友链申请
     * @param id
     * @param pass
     * @return
     */
    public int handleLinkPass(Long id, Boolean pass) {
        Link link = selectLinkById(id);
        if (Objects.isNull(link)) {
            throw new CustomException("友链不存在");
        }
        if (!pass) {
            //todo 发送email
            return linkMapper.deleteLinkById(id, SecurityUtils.getUsername());
        }
        link.setStatus(true);
        return linkMapper.updateLink(link);
    }


}
