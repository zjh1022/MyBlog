package com.zjh.myblog.controller.front;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.vo.FrontMenu;
import com.zjh.myblog.service.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther zhengjianghai
 * @create 2022-01-26 9:40
 */
@RestController
@RequestMapping("/f")
public class FrontMenuController extends BaseController {

    @Autowired
    FrontService frontService;

    /**
     * 网站菜单
     */
    @GetMapping("/menus")
    public AjaxResult menu() {
        List<FrontMenu> menuList = new ArrayList<>();
        menuList.add(new FrontMenu("时光轴", 1, false, "/archive"));
        menuList.add(new FrontMenu("友链", 2, false, "/link"));
//        menuList.add(new FrontMenu("留言", 3, false, "/leaveComment"));
        menuList.add(new FrontMenu("关于", 3, false, "/about"));
        return AjaxResult.success(menuList);
    }
}
