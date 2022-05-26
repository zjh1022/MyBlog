package com.zjh.myblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
/**
    * 系统访问记录
    */
@ApiModel(value="系统访问记录")
@Data
@Builder
public class LoginLog implements Serializable {
    /**
    * 访问ID
    */
    @ApiModelProperty(value="访问ID")
    private Long id;

    /**
    * 用户账号
    */
    @ApiModelProperty(value="用户账号")
    private String userName;

    /**
    * 登录IP地址
    */
    @ApiModelProperty(value="登录IP地址")
    private String ip;

    /**
    * 登录地点
    */
    @ApiModelProperty(value="登录地点")
    private String location;

    /**
    * 浏览器类型
    */
    @ApiModelProperty(value="浏览器类型")
    private String browser;

    /**
    * 操作系统
    */
    @ApiModelProperty(value="操作系统")
    private String os;

    /**
    * 登录状态（1成功 0失败）
    */
    @ApiModelProperty(value="登录状态（1成功 0失败）")
    private Integer status;

    /**
    * 提示消息
    */
    @ApiModelProperty(value="提示消息")
    private String msg;

    /**
    * 访问时间
    */
    @ApiModelProperty(value="访问时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value="")
    private String createBy;

    @ApiModelProperty(value="")
    private String deleteBy;

    @ApiModelProperty(value="")
    private LocalDateTime deleteTime;

    private static final long serialVersionUID = 1L;
}