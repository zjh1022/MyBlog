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
    * 角色信息表
    */
@ApiModel(value="角色信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BaseEntity implements Serializable {
    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    private Long id;

    /**
    * 角色名称
    */
    @ApiModelProperty(value="角色名称")
    private String roleName;

    /**
    * 角色权限字符串
    */
    @ApiModelProperty(value="角色权限字符串")
    private String roleKey;

    /**
    * 显示顺序
    */
    @ApiModelProperty(value="显示顺序")
    private Integer roleSort;

    /**
    * 角色状态（0正常 1停用）
    */
    @ApiModelProperty(value="角色状态（0正常 1停用）")
    private String status;


    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    private boolean flag = false;

    /**
     * 菜单组
     */
    private Long[] menuIds;

    public Role(Long id) {
        this.id = id;
    }



    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    private static final long serialVersionUID = 1L;
}