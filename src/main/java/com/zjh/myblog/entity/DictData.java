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
    * 字典数据表
    */
@ApiModel(value="字典数据表")
@Data
@Builder
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class DictData extends BaseEntity implements Serializable {
    /**
    * 字典编码
    */
    @ApiModelProperty(value="字典编码")
    private Long dictCode;

    /**
    * 字典排序
    */
    @ApiModelProperty(value="字典排序")
    private Integer dictSort;

    /**
    * 字典标签
    */
    @ApiModelProperty(value="字典标签")
    private String dictLabel;

    /**
    * 字典键值
    */
    @ApiModelProperty(value="字典键值")
    private String dictValue;

    /**
    * 字典类型
    */
    @ApiModelProperty(value="字典类型")
    private String dictType;

    /**
    * 样式属性（其他样式扩展）
    */
    @ApiModelProperty(value="样式属性（其他样式扩展）")
    private String cssClass;

    /**
    * 表格回显样式
    */
    @ApiModelProperty(value="表格回显样式")
    private String listClass;

    /**
    * 是否默认（Y是 N否）
    */
    @ApiModelProperty(value="是否默认（Y是 N否）")
    private String isDefault;

    /**
    * 状态（0正常 1停用）
    */
    @ApiModelProperty(value="状态（0正常 1停用）")
    private String status;



    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    private static final long serialVersionUID = 1L;
}