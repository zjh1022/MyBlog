package com.zjh.myblog.execption;

/**
 * @className: FileNameLengthLimitExceededException
 * @description: if the name of file is too long,will throw this exception
 */
public class FileNameLengthLimitExceededException extends FileException {

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
