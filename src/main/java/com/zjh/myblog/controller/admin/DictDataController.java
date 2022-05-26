package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.DictData;
import com.zjh.myblog.service.DictDataService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-01-28 10:56
 */
@RestController
@RequestMapping("/system/dict/data")
public class DictDataController extends BaseController {

    @Resource
    private DictDataService dictDataService;

    @PreAuthorize("@permissionService.hasPermission('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(DictData dictData) {
        startPage();
        List<DictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize("@permissionService.hasPermission('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode) {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @PreAuthorize("@permissionService.hasPermission('system:dict:query')")
    @GetMapping(value = "/dictType/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        return AjaxResult.success(dictDataService.selectDictDataByType(dictType));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@permissionService.hasPermission('system:dict:add')")
    @PostMapping
    public AjaxResult add(@RequestBody DictData dict) {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@permissionService.hasPermission('system:dict:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody DictData dict) {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@permissionService.hasPermission('system:dict:remove')")
    @DeleteMapping("/{dictCode}")
    public AjaxResult remove(@PathVariable Long dictCode) {
        return toAjax(dictDataService.deleteDictDataById(dictCode));
    }

}
