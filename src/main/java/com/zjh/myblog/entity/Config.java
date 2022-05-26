package com.zjh.myblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

*/
/**
    * 参数配置表
    */
@ApiModel(value="参数配置表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Config implements Serializable {
    /**
    * 参数主键
    */
    @ApiModelProperty(value="参数主键")
    private Integer id;

    /**
    * 参数键名
    */
    @ApiModelProperty(value="参数键名")
    private String configKey;

    /**
    * 参数键值
    */
    @ApiModelProperty(value="参数键值")
    private String configValue;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
    * 创建者
    */
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
    * 更新者
    */
    @ApiModelProperty(value="更新者")
    private String updateBy;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value="")
    private String deleteBy;

    @ApiModelProperty(value="")
    private LocalDateTime deleteTime;

    private static final long serialVersionUID = 1L;
}