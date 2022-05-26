package com.zjh.myblog.service;

import com.zjh.myblog.entity.Notice;
import com.zjh.myblog.mapper.NoticeMapper;
import com.zjh.myblog.utlis.ConvertUtils;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-18 16:30
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public Notice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }


    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }


    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改
     * @param notice
     * @return
     */
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    public int deleteNoticeByIds(String ids) {
        String loginUsername = SecurityUtils.getUsername();
        return noticeMapper.deleteNoticeByIds(ConvertUtils.toLongArray(ids), loginUsername);
    }

}
