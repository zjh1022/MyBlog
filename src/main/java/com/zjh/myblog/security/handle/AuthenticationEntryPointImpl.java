package com.zjh.myblog.security.handle;


import com.alibaba.fastjson.JSON;
import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.utlis.IpUtils;
import com.zjh.myblog.utlis.ServletUtils;
import com.zjh.myblog.utlis.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @className: AuthenticationEntryPointImpl
 * @description: 认证失败处理类返回未授权
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.error("越权获取: \ncurrent IP:{}\nrequest URI :{}\n", IpUtils.getIpAddr(request), request.getRequestURI());
        String msg = StringUtils.format("越权获取：{}，认证失败，无法访问系统资源 ", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.UNAUTHORIZED, msg)));
    }
}
