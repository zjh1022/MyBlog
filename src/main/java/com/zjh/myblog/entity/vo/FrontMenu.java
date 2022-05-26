package com.zjh.myblog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: 前台菜单
 * @description:
 * @author: zhengjianghai
 * @date: 2022/1/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FrontMenu implements Serializable {
    /**
     * 显示名
     */
    private String title;
    /**
     * 显示顺序
     */
    private Integer order;
    /**
     * 是否当前窗口打开,true表示当前窗口打开
     */
    private Boolean target;
    /**
     * 路径
     */
    private String url;
}
