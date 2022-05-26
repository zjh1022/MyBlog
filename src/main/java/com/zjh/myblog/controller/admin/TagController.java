package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Tag;
import com.zjh.myblog.service.TagService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-06 15:59
 */
@RestController
@RequestMapping("blog/tag")
public class TagController extends BaseController {

    @Autowired
    TagService tagService;


    @PreAuthorize("@permissionService.hasPermission('blog:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tag tag) {
        startPage();
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:tag:add')")
    @PostMapping()
    public AjaxResult add(@RequestBody Tag tag) {
        tag.setCreateBy(SecurityUtils.getUsername());
        return toAjax(tagService.insertTag(tag));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(tagService.selectTagById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:tag:edit')")
    @PutMapping()
    public AjaxResult edit(@RequestBody Tag tag) {
        return toAjax(tagService.updateTag(tag));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:tag:remove')")

    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(tagService.deleteTagByIds(ids));
    }
}
