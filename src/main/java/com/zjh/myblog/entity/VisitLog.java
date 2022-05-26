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
    * 访问日志表
    */
@ApiModel(value="访问日志表")
@Data
@Builder
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class VisitLog extends BaseEntity implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

    /**
    * IP地址
    */
    @ApiModelProperty(value="IP地址")
    private String ip;

    /**
    * 页面ID
    */
    @ApiModelProperty(value="页面ID")
    private String pageId;

    /**
    * 地理位置
    */
    @ApiModelProperty(value="地理位置")
    private String location;

    /**
    * 浏览器
    */
    @ApiModelProperty(value="浏览器")
    private String browser;

    /**
    * 操作系统
    */
    @ApiModelProperty(value="操作系统")
    private String os;

    @ApiModelProperty(value="")
    private String entryUrl;

    /**
    * 访问URL地址
    */
    @ApiModelProperty(value="访问URL地址")
    private String url;

    @ApiModelProperty(value="")
    private String errorMsg;

    /**
    * 状态,1表示成功,0表示失败
    */
    @ApiModelProperty(value="状态,1表示成功,0表示失败")
    private boolean status;

    /**
    * 访问模块
    */
    @ApiModelProperty(value="访问模块")
    private String title;

    /**
    * 爬虫
    */
    @ApiModelProperty(value="爬虫")
    private String spider;

    private static final long serialVersionUID = 1L;
}