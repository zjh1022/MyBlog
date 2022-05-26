package com.zjh.myblog.execption;

/**
 * @className: FileException
 * @description: file exception class

 */
public class FileException extends BaseException {

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
