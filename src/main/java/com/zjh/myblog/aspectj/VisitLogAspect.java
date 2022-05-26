package com.zjh.myblog.aspectj;

import com.zjh.myblog.entity.VisitLog;
import com.zjh.myblog.manager.AsyncFactory;
import com.zjh.myblog.manager.AsyncManager;
import com.zjh.myblog.utlis.IpUtils;
import com.zjh.myblog.utlis.ServletUtils;
import com.zjh.myblog.utlis.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-19 15:47
 */
@Aspect
@Component
@Slf4j
public class VisitLogAspect {

    @Autowired
    RedisTemplate redisTemplate;

    @Pointcut("execution(* com.zjh.myblog.controller.front.FrontMenuController.*(..)) ")
    public void logPointCut() {
    }

    /**
     * 前置通知 用于拦截操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {

            VisitLog visitLog = new VisitLog();
            visitLog.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
                if (e != null) {
                    visitLog.setStatus(false);
                    visitLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
                } else {
                    visitLog.setStatus(true);
                }
                // 保存数据库
                AsyncManager.me().execute(AsyncFactory.recordVisitLog(visitLog));

        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage(), exp);
        }
    }

}
