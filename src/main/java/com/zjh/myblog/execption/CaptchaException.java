package com.zjh.myblog.execption;

/**
 * @auther zhengjianghai
 * @create 2022-01-22 18:03
 */
public class CaptchaException extends RuntimeException{

    public CaptchaException(){
        super("验证码错误！");
    }
}
