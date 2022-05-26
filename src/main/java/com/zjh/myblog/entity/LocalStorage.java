package com.zjh.myblog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: LocalStorage
 * @description:
 * @author: zhengjianghai
 * @date: 2022/2/5
 */
@Data
@NoArgsConstructor
public class LocalStorage extends BaseEntity implements Serializable {
    private Long id;
    /**
     * 文件真实名称
     */
    private String realName;
    /**
     * 文件名
     */
    private String name;
    /**
     * 后缀
     */
    private String suffix;
    /**
     * 路径
     */
    private String path;
    /**
     * 类型
     */
    private String type;
    /**
     * 大小
     */
    private String size;

    public LocalStorage(String realName, String name, String suffix, String path, String type, String size) {
        this.realName = realName;
        this.name = name;
        this.suffix = suffix;
        this.path = path;
        this.type = type;
        this.size = size;
    }
}
