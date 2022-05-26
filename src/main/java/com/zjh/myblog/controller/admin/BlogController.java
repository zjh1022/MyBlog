package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Blog;
import com.zjh.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-05 14:10
 */
@RestController
@RequestMapping("/blog/blog")
public class BlogController extends BaseController {
    @Autowired
    private BlogService blogService;



    @PreAuthorize("@permissionService.hasPermission('blog:blog:list')")

    @GetMapping("/list")
    public TableDataInfo list(Blog blog) {
        startPage();
        List<Blog> list = blogService.selectBlogList(blog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:add')")
    @PostMapping()
    public AjaxResult add(@Validated(Blog.Publish.class) @RequestBody Blog blog) {
        return toAjax(blogService.insertBlog(blog));
    }

    /**
     * 存草稿
     * @param blog
     * @return
     */
    @PreAuthorize("@permissionService.hasPermission('blog:blog:add')")
    @PostMapping("draft")
    public AjaxResult draft(@Validated(Blog.Draft.class) @RequestBody Blog blog) {
        return toAjax(blogService.insertBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @PutMapping()
    public AjaxResult edit(@Validated(Blog.Publish.class) @RequestBody Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @PutMapping("draft")
    public AjaxResult editDraft(@RequestBody @Validated(Blog.Draft.class) Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @PutMapping("support")
    public AjaxResult editSupport(@RequestBody Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @PutMapping("comment")
    public AjaxResult editComment(@RequestBody Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(blogService.selectBlogById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:remove')")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        return toAjax(blogService.deleteBlogById(id));
    }





    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @GetMapping("tag/{query}")
    public TableDataInfo getCommonTag(@PathVariable String query) {
        return getDataTable(blogService.selectBlogTagList(query));
    }
}
