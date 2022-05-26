package com.zjh.myblog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
/**
    * 博客标签表
    */
@ApiModel(value="博客标签表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag extends BaseEntity implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

    /**
    * 标签轮廓颜色
    */
    @ApiModelProperty(value="标签轮廓颜色")
    private String color;

    /**
    * 标签名
    */
    @ApiModelProperty(value="标签名")
    private String title;

    /**
     * 关联数量
     */
    private Long count;


    public Tag(String title, String color) {
        this.color = color;
        this.title = title;
    }


    private static final long serialVersionUID = 1L;
}