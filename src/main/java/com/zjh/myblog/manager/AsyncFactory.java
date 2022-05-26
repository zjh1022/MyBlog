package com.zjh.myblog.manager;

import com.zjh.myblog.entity.VisitLog;
import com.zjh.myblog.mapper.VisitLogMapper;
import com.zjh.myblog.utlis.AddressUtils;
import com.zjh.myblog.utlis.ServletUtils;
import com.zjh.myblog.utlis.spring.SpringUtils;
import eu.bitwalker.useragentutils.UserAgent;

import java.util.TimerTask;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-19 15:36
 */
public class AsyncFactory {

    private AsyncFactory() {

    }
    /**
     *
     *
     * @param visitLog visitLog
     * @return timeTask
     */
    public static TimerTask recordVisitLog(final VisitLog visitLog) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        return new TimerTask() {
            @Override
            public void run() {
                visitLog.setOs(userAgent.getOperatingSystem().getName());
                visitLog.setBrowser(userAgent.getBrowser().getName());
                visitLog.setLocation(AddressUtils.getCityInfoByIp(visitLog.getIp()));
                SpringUtils.getBean(VisitLogMapper.class).insertVisitLog(visitLog);
            }
        };
    }

}
