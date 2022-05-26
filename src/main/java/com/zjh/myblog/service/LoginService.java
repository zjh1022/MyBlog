package com.zjh.myblog.service;

import com.zjh.myblog.config.RedisCacheService;
import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.execption.CaptchaException;
import com.zjh.myblog.execption.CaptchaExpireException;
import com.zjh.myblog.execption.CustomException;
import com.zjh.myblog.execption.UserPasswordNotMatchException;
import com.zjh.myblog.security.LoginUser;
import com.zjh.myblog.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther zhengjianghai
 * @create 2022-01-22 17:48
 */
@Service
public class LoginService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisCacheService redisCacheService;
    @Resource
    private AuthenticationManager authenticationManager;

    public String login(String username, String password, String code, String uuid) {
        String key = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCacheService.getCacheObject(key);
        redisCacheService.deleteObject(key);
        if (null == captcha) {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
        //进行用户验证
        Authentication authentication= null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser user = (LoginUser)authentication.getPrincipal();
        return(tokenService.createToken(user));
    }
}
