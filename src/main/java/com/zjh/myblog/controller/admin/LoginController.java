package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.entity.Menu;
import com.zjh.myblog.entity.User;
import com.zjh.myblog.security.LoginUser;
import com.zjh.myblog.security.service.TokenService;
import com.zjh.myblog.service.LoginService;
import com.zjh.myblog.service.MenuService;
import com.zjh.myblog.service.SysPermissionService;
import com.zjh.myblog.utlis.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @auther zhengjianghai
 * @create 2022-01-22 15:26
 */
@RestController
@Api("登录验证")
public class LoginController {

    @Resource
    private LoginService loginService;
    @Resource
    private TokenService tokenService;
    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private MenuService menuService;



    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid) {
        String token = loginService.login(username, password, code, uuid);
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        //获取到用户
        User user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success();
        //获取所属角色
        Set<String> roles = sysPermissionService.getRolePermission(user);
        //获取权限集合
        Set<String> permissions = sysPermissionService.getMenuPermission(user);
        ajax.put("user",user);
        ajax.put("roles",roles);
        ajax.put("perimissions",permissions);
        return ajax;

    }
    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @ApiOperation("路由信息")
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        User user = loginUser.getUser();
        List<Menu> menus = menuService.selectMenuTreeByUserId(user.getId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

}
