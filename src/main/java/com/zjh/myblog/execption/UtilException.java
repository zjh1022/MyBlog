package com.zjh.myblog.execption;

/**
 * @auther zhengjianghai
 * @create 2022-01-21 9:32
 */
public class UtilException extends RuntimeException{
    private static final long serialVersionUID = 1546710319173649573L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
