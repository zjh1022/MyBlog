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
@ApiModel(value="tag_mapping")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagMapping implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="")
    private Long tagId;

    @ApiModelProperty(value="")
    private Long blogId;

    private static final long serialVersionUID = 1L;
}