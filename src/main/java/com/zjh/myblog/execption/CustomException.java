package com.zjh.myblog.execption;

import org.springframework.http.HttpStatus;

/**
 * @auther zhengjianghai
 * @create 2022-01-20 20:12
 */
public class CustomException extends RuntimeException{
    private HttpStatus code;

    private String message;

    public CustomException(String message){
        this.message=message;
    }

    public CustomException(String message, HttpStatus code) {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getCode() {
        return code;
    }
}
