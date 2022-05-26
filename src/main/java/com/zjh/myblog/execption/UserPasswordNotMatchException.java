package com.zjh.myblog.execption;


public class UserPasswordNotMatchException extends RuntimeException {

    public UserPasswordNotMatchException() {
        super("用户密码不匹配", null);
    }
}
