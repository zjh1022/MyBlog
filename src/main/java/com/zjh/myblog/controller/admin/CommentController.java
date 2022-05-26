package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Comment;
import com.zjh.myblog.service.CommentService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 评论类
 * @auther zhengjianghai
 * @create 2022-02-06 15:42
 */
@RestController
@RequestMapping("/blog/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;


    @PreAuthorize("@permissionService.hasPermission('blog:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Comment comment) {
        startPage();
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(commentService.selectCommentById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:add')")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Comment comment) {
        comment.setCreateBy(SecurityUtils.getUsername());
        return toAjax(commentService.insertComment(comment));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated Comment comment) {
        comment.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(commentService.updateComment(comment));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:edit')")
    @PutMapping("/{id}/display/{display}")
    public AjaxResult edit(@PathVariable Long id, @PathVariable Boolean display) {
        Comment comment = new Comment();
        comment.setDisplay(display);
        comment.setId(id);
        comment.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(commentService.updateComment(comment));
    }

    @PreAuthorize("@permissionService.hasPermission('system:config:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(commentService.deleteCommentByIds(ids));
    }


}
