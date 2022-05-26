package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.User;
import com.zjh.myblog.service.RoleService;
import com.zjh.myblog.service.UserService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户个人中心
 * @auther zhengjianghai
 * @create 2022-01-27 9:05
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;


    /**
     * 获取用户列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user) {
        startPage();
        List<User> users = userService.selectUserList(user);
        return getDataTable(users);

    }
    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        AjaxResult ajax = AjaxResult.success(userService.selectUserById(id));
        ajax.put("roleIds", roleService.selectRoleListByUserId(id));
        return ajax;
    }


    /**
     * 新增用户
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:add')")
    @PostMapping
    public AjaxResult add(@RequestBody User user) {
        if (Constants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody User user) {
        userService.checkUserAllowed(user);
        if (Constants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (Constants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(userService.deleteUserByIds(ids));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:edit')")
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody User user) {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@permissionService.hasPermission('system:user:edit')")
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody User user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

}
