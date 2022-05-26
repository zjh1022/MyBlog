package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Role;
import com.zjh.myblog.service.RoleService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 角色信息
 * @auther zhengjianghai
 * @create 2022-01-27 14:39
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色
     * @param role
     * @return
     */
    @PreAuthorize("@permissionService.hasPermission('system:role:list')")
    @GetMapping("/list")
    public TableDataInfo list(Role role) {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }


    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize("@permissionService.hasPermission('system:role:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(roleService.selectRoleById(id));
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@permissionService.hasPermission('system:role:add')")
    @PostMapping
    public AjaxResult add(@RequestBody Role role) {
        if (Constants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (Constants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(SecurityUtils.getUsername());
        return toAjax(roleService.insertRole(role));

    }
    /**
     * 修改保存角色
     */
    @PreAuthorize("@permissionService.hasPermission('system:role:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody Role role) {
        roleService.checkRoleAllowed(role);
        if (Constants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (Constants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRole(role));
    }

    /**
     * 修改保存数据权限
     */
    @PreAuthorize("@permissionService.hasPermission('system:role:edit')")
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody Role role) {
        roleService.checkRoleAllowed(role);
        return toAjax(roleService.authDataScope(role));
    }
    /**
     * 状态修改
     */
    @PreAuthorize("@permissionService.hasPermission('system:role:edit')")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody Role role) {
        roleService.checkRoleAllowed(role);
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRoleStatus(role));
    }

    @PreAuthorize("@permissionService.hasPermission('system:role:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(roleService.deleteRoleByIds(ids));
    }

    /**
     * 获取角色选择框列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:role:query')")
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        return AjaxResult.success(roleService.selectRoleAll());
    }
}
