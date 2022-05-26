package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.constant.Constants;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Menu;
import com.zjh.myblog.service.MenuService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-01-27 15:37
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    @Resource
    private MenuService menuService;


    /**
     * 获取菜单列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:list')")
    @GetMapping("/list")
    public AjaxResult list(Menu menu) {
        return AjaxResult.success(menuService.selectMenuList(menu));
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(menuService.selectMenuById(id));
    }

    /**
     * 获取菜单下拉树列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping("/treeselect")
    public AjaxResult treeselect(Menu dept) {
        List<Menu> menus = menuService.selectMenuList(dept);
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    @ResponseBody
    public AjaxResult roleMenuTreeselect(@PathVariable Long roleId) {
        List<Menu> menus = menuService.selectMenuList(new Menu());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:add')")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Menu menu) {
        if (Constants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody Menu menu) {
        if (Constants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:remove')")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        if (menuService.hasChildByMenuId(id)) {
            return AjaxResult.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(id)) {
            return AjaxResult.error("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(id));
    }
}
