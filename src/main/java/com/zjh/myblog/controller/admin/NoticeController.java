package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Notice;
import com.zjh.myblog.service.NoticeService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 公告
 * @auther zhengjianghai
 * @create 2022-02-20 17:07
 */
@RestController
@RequestMapping("/system/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;
    /**
     * 获取通知公告列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(Notice notice) {
        startPage();
        List<Notice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(noticeService.selectNoticeById(id));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:add')")
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice) {
        notice.setCreateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody Notice notice) {
        notice.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@permissionService.hasPermission('system:notice:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }
}
