package com.zjh.myblog.entity;

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
    * 轮播图
    */
@ApiModel(value="轮播图")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carousel extends BaseEntity implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

    /**
    * 显示文本
    */
    @ApiModelProperty(value="显示文本")
    private String description;

    /**
    * 点击次数
    */
    @ApiModelProperty(value="点击次数")
    private Long click;

    /**
    * 图片URL
    */
    @ApiModelProperty(value="图片URL")
    private String headerImg;

    /**
    * 是否显示
    */
    @ApiModelProperty(value="是否显示")
    private Boolean display;

    /**
    * 是否当前窗口打开
    */
    @ApiModelProperty(value="是否当前窗口打开")
    private Boolean target;

    /**
    * 链接地址
    */
    @ApiModelProperty(value="链接地址")
    private String url;

    /**
    * header
    */
    @ApiModelProperty(value="header")
    private String title;


    private static final long serialVersionUID = 1L;
}