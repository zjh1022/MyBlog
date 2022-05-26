package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.User;
import com.zjh.myblog.security.LoginUser;
import com.zjh.myblog.security.service.TokenService;
import com.zjh.myblog.service.UserService;
import com.zjh.myblog.utlis.SecurityUtils;
import com.zjh.myblog.utlis.ServletUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description 个人信息 业务处理
 * @auther zhengjianghai
 * @create 2022-01-27 16:42
 */
@RestController
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        User user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @PutMapping
    public AjaxResult updateProfile(@RequestBody User user) {
        return toAjax(userService.updateUserProfile(user));
    }

    /**
     * 重置密码
     */

    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        return toAjax(userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)));
    }
}
