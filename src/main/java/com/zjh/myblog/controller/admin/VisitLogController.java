package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.VisitLog;
import com.zjh.myblog.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-03-02 17:46
 */
@RestController
@RequestMapping("/log/visitLog")
public class VisitLogController extends BaseController {

    @Autowired
    private VisitLogService visitLogService;

    @PreAuthorize("@permissionService.hasPermission('monitor:visitLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(VisitLog visitLog) {
        startPage();
        List<VisitLog> list = visitLogService.selectVisitLogList(visitLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:visitLog:remove')")
    @DeleteMapping("{ids}")
    public AjaxResult deleteLoginLog(@PathVariable String ids) {
        return toAjax(visitLogService.deleteVisitLogByIds(ids));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:visitLog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult cleanVisitLog() {
        visitLogService.cleanVisitLog();
        return AjaxResult.success();
    }
}
