package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.LocalStorage;
import com.zjh.myblog.service.LocalStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description  本地存储
 * @auther zhengjianghai
 * @create 2022-02-01 16:50
 */
@Api("本地存储")
@RestController
@RequestMapping("/tool/localStorage")
public class LocalStorageController extends BaseController {
    @Autowired
    LocalStorageService localStorageService;

    @ApiOperation("获取本地存储文件")
    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:list')")
    @GetMapping("/list")
    public TableDataInfo list(LocalStorage localStorage) {
        startPage();
        List<LocalStorage> list = localStorageService.selectLocalStorageList(localStorage);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:upload')")
    @PostMapping
    @ApiOperation("文件上传")
    public AjaxResult upload(@RequestParam String name, @RequestParam MultipartFile file) {
        return toAjax(localStorageService.upload(name, file));
    }

    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:upload')")
    @PostMapping("/upload")
    @ApiOperation("文件上传返回url")
    public AjaxResult upload1(@RequestParam MultipartFile file) {
        return AjaxResult.success("上传成功",localStorageService.upload1(file));
    }

    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:edit')")
    @PutMapping
    public AjaxResult upload(@RequestBody LocalStorage localStorage) {
        return toAjax(localStorageService.updateLocalStorage(localStorage));
    }


    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:remove')")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        return toAjax(localStorageService.deleteLocalStorage(id));
    }
}
