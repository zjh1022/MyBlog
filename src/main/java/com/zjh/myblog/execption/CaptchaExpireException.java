package com.zjh.myblog.execption;

/**
 * @auther zhengjianghai
 * @create 2022-01-22 18:01
 */
public class CaptchaExpireException extends RuntimeException{

        public CaptchaExpireException(){
            super("验证码已过期！");
        }
}
