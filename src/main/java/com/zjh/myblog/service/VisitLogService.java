package com.zjh.myblog.service;

import com.zjh.myblog.entity.VisitLog;
import com.zjh.myblog.mapper.VisitLogMapper;
import com.zjh.myblog.utlis.ConvertUtils;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-03-02 17:54
 */
@Service
public class VisitLogService {
    @Autowired
    VisitLogMapper visitLogMapper;

    
    public List<VisitLog> selectVisitLogList(VisitLog visitLog) {
        return visitLogMapper.selectVisitLogList(visitLog);
    }

    
    public int deleteVisitLogByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return visitLogMapper.deleteVisitLogByIds(ConvertUtils.toLongArray(ids), username);
    }

    
    public void cleanVisitLog() {
        String username = SecurityUtils.getUsername();
        visitLogMapper.cleanVisitLog(username);
    }

    
    public int insertVisitLog(VisitLog visitLog) {
        return visitLogMapper.insertVisitLog(visitLog);
    }
}
