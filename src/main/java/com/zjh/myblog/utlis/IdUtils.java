package com.zjh.myblog.utlis;

import java.util.UUID;

/**
 * @auther zhengjianghai
 * @create 2022-01-20 18:37
 */
public class IdUtils {

    private IdUtils() {
    }

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString();
    }
}
