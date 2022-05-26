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
    * 通知公告表
    */
@ApiModel(value="通知公告表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity implements Serializable {
    /**
    * 公告ID
    */
    @ApiModelProperty(value="公告ID")
    private Integer id;

    /**
    * 公告标题
    */
    @ApiModelProperty(value="公告标题")
    private String title;

    /**
    * 公告类型（1通知 2公告）
    */
    @ApiModelProperty(value="公告类型（1通知 2公告）")
    private String type;

    /**
    * 公告内容
    */
    @ApiModelProperty(value="公告内容")
    private String content;

    @ApiModelProperty(value="")
    private String htmlContent;

    /**
    * 公告状态（0正常 1关闭）
    */
    @ApiModelProperty(value="公告状态（0正常 1关闭）")
    private String status;


    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}