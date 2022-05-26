package com.zjh.myblog.security.filter;


import com.zjh.myblog.security.LoginUser;
import com.zjh.myblog.security.service.TokenService;
import com.zjh.myblog.utlis.SecurityUtils;
import com.zjh.myblog.utlis.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: JwtAuthenticationTokenFilter
 * @description: token过滤器 验证token有效性
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        log.info("当前请求路径: {}", request.getRequestURL());
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
