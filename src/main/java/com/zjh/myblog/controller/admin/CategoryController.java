package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.Category;
import com.zjh.myblog.service.CategoryService;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 分类controller
 * @auther zhengjianghai
 * @create 2022-02-06 15:12
 */
@RestController
@RequestMapping("/blog/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:add')")
    @PostMapping()
    public AjaxResult add(@RequestBody @Validated Category category) {
        category.setCreateBy(SecurityUtils.getUsername());
        return toAjax(categoryService.insertCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(categoryService.selectCategoryById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:edit')")
    @PutMapping()
    public AjaxResult edit(@RequestBody @Validated Category category) {
        return toAjax(categoryService.updateCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }
}
