package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.entity.dto.LineChartData;
import com.zjh.myblog.service.DashBoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description  后台首页
 * @auther zhengjianghai
 * @create 2022-02-07 14:54
 */
@Api("首页展示controller")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashBoardService dashBoardService;

    @ApiOperation("获取首页数据")
    @GetMapping("/panelGroup")
    public AjaxResult getPanelGroupData() {
        Map<String, Long> panelGroupData = dashBoardService.getPanelGroupData();
        return AjaxResult.success(panelGroupData);
    }

    @GetMapping("lineChartData/{type}")
    public AjaxResult getLineChartData(@PathVariable String type) {
        LineChartData<Long> lineChartData = dashBoardService.getLineChartData(type);
        return AjaxResult.success(lineChartData);
    }

    @GetMapping("/spiderData")
    public AjaxResult getSpiderData() {
        List<Map<String, Long>> result = dashBoardService.getSpiderData();
        return AjaxResult.success(result);
    }
}
