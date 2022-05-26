package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Link;
import com.zjh.myblog.service.LinkService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-01-27 15:53
 */
@RestController
@RequestMapping("/system/link")
public class LinkController extends BaseController {

    @Resource
    private LinkService linkService;


    @PreAuthorize("@permissionService.hasPermission('blog:link:list')")
    @GetMapping("/list")
    public TableDataInfo list(Link link) {
        startPage();
        List<Link> list = linkService.selectLinkList(link);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(linkService.selectLinkById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:add')")
    @PostMapping()
    public AjaxResult add(@RequestBody @Validated Link link) {
        link.setCreateBy(SecurityUtils.getUsername());
        return toAjax(linkService.insertLink(link));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:edit')")
    @PutMapping()
    public AjaxResult edit(@RequestBody @Validated Link link) {
        link.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(linkService.updateLink(link));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:edit')")
    @PutMapping("/pass/{id}/{pass}")
    public AjaxResult handlePass(@PathVariable Long id, @PathVariable Boolean pass) {
        return toAjax(linkService.handleLinkPass(id, pass));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable String ids) {
        return toAjax(linkService.deleteLinkByIds(ids));
    }



}
