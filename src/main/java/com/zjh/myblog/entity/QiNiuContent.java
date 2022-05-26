package com.zjh.myblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
@description    
@auther zhengjianghai 
    
@create 2022-03-26 14:40

*/

/**
 * 七牛云数据本地缓存
 */
@ApiModel(value = "七牛云数据本地缓存")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QiNiuContent extends BaseEntity implements Serializable {
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 七牛云文件名称
     */
    @ApiModelProperty(value = "七牛云文件名称")
    private String name;

    /**
     * 空间
     */
    @ApiModelProperty(value = "空间")
    private String bucket;

    /**
     * 文件大小
     */
    @ApiModelProperty(value = "文件大小")
    private String size;

    /**
     * 文件访问地址
     */
    @ApiModelProperty(value = "文件访问地址")
    private String url;

    /**
     * 文件后缀
     */
    @ApiModelProperty(value = "文件后缀")
    private String suffix;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    private static final long serialVersionUID = 1L;
}