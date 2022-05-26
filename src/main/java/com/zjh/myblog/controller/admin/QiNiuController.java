package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.QiNiuContent;
import com.zjh.myblog.service.QiNiuContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-03-26 16:04
 */
@RestController
@RequestMapping("/tool/qiNiu")
public class QiNiuController extends BaseController {

    @Autowired
    private QiNiuContentService qiNiuService;

    @GetMapping("/list")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:list')")
    public TableDataInfo list(QiNiuContent qiNiuContent) {
        startPage();
        List<QiNiuContent> qiNiuContentList = qiNiuService.selectContentList(qiNiuContent);
        return getDataTable(qiNiuContentList);
    }


    @PostMapping
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:upload')")
    public AjaxResult upload(@RequestParam MultipartFile file) {
        QiNiuContent qiNiuContent = qiNiuService.upload(file);
        return AjaxResult.success(qiNiuContent);
    }

    @PreAuthorize("@permissionService.hasPermission('system:qiNiu:synchronize')")
    @PostMapping("/synchronize")
    public AjaxResult synchronize() {
        return AjaxResult.success(qiNiuService.synchronize());
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:remove')")
    public AjaxResult delete(@PathVariable String ids) {
        return toAjax(qiNiuService.deleteQiNiuContent(ids));
    }

    @GetMapping("/download/{id}")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:download')")
    public AjaxResult download(@PathVariable Long id) {
        return AjaxResult.success(qiNiuService.getDownloadUrl(id));
    }

}
