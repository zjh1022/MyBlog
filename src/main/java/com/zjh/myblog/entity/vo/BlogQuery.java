package com.zjh.myblog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther zhengjianghai
 * @create 2022-01-26 9:56
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogQuery implements Serializable {
    /**
     * 分类Id
     */
    private Long categoryId;
    /**
     * 是否推荐
     */
    private Boolean support;
    /**
     * 查询开始时间
     */
    private Date beginTime;
    /**
     * 查询结束时间
     */
    private Date endTime;
}
