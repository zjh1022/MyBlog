package com.zjh.myblog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
    
@auther zhengjianghai 
    
@create 2022-01-20 19:26

/**
    * 菜单权限表
    */
@ApiModel(value="菜单权限表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu extends BaseEntity implements Serializable {
    /**
    * 菜单ID
    */
    @ApiModelProperty(value="菜单ID")
    private Long id;

    /**
    * 菜单名称
    */
    @ApiModelProperty(value="菜单名称")
    private String menuName;

    /**
    * 父菜单ID
    */
    @ApiModelProperty(value="父菜单ID")
    private Long parentId;

    /**
    * 显示顺序
    */
    @ApiModelProperty(value="显示顺序")
    private Integer orderNum;

    /**
    * 路由地址
    */
    @ApiModelProperty(value="路由地址")
    private String path;

    /**
    * 组件路径
    */
    @ApiModelProperty(value="组件路径")
    private String component;

    /**
    * 是否为外链（0是 1否）
    */
    @ApiModelProperty(value="是否为外链（0是 1否）")
    private String isFrame;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    @ApiModelProperty(value="菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    /**
    * 菜单状态（0显示 1隐藏）
    */
    @ApiModelProperty(value="菜单状态（0显示 1隐藏）")
    private String visible;

    /**
    * 权限标识
    */
    @ApiModelProperty(value="权限标识")
    private String perms;

    /**
    * 菜单图标
    */
    @ApiModelProperty(value="菜单图标")
    private String icon;

    /**
    * 创建者
    */
    @ApiModelProperty(value="创建者")
    private String createBy;



    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 子菜单
     */
    private List<Menu> children = new ArrayList<>();

    private static final long serialVersionUID = 1L;
}