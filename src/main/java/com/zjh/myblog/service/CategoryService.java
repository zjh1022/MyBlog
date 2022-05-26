package com.zjh.myblog.service;

import com.zjh.myblog.entity.Blog;
import com.zjh.myblog.entity.Category;
import com.zjh.myblog.mapper.BlogMapper;
import com.zjh.myblog.mapper.CategoryMapper;
import com.zjh.myblog.utlis.ConvertUtils;
import com.zjh.myblog.utlis.DateUtils;
import com.zjh.myblog.utlis.SecurityUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description  分类service
 * @auther zhengjianghai
 * @create 2022-02-06 15:14
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BlogMapper blogMapper;

    /**
     *查询分类
     * @param id
     * @return
     */
    public Category selectCategoryById(Long id) {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询分类列表
     * @param bgCategory
     * @return
     */
    public List<Category> selectCategoryList(Category bgCategory) {
        List<Category> categoryList = categoryMapper.selectCategoryList(bgCategory);
        List<Long> categoryIds = categoryList.stream().map(Category::getId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(categoryIds)) {
            return categoryList;
        }
        List<Blog> blogList = blogMapper.selectBlogListByCategoryIds(categoryIds);
        for (Category category : categoryList) {
            List<Blog> collect = blogList.stream().filter(e -> category.getId().equals(e.getCategoryId())).collect(Collectors.toList());
            category.setBlogList(collect);
        }
        return categoryList;
    }

    /**
     * 新增分类
     * @param bgCategory
     * @return
     */
    public int insertCategory(Category bgCategory) {
        return categoryMapper.insertCategory(bgCategory);
    }

    /**
     * 修改分类
     * @param bgCategory
     * @return
     */
    public int updateCategory(Category bgCategory) {

        bgCategory.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(bgCategory);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int deleteCategoryByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return categoryMapper.deleteCategoryByIds(ConvertUtils.toLongArray(ids), username);
    }

    /**
     * 删除分类信息
     * @param id
     * @return
     */
    public int deleteCategoryById(Long id) {
        String username = SecurityUtils.getUsername();
        return categoryMapper.deleteCategoryById(id, username);
    }

    /**
     * 获取support的分类
     * @return  List<Category>
     */
    public List<Category> selectSupportCategory() {
        return categoryMapper.selectSupportBlogCategoryList();
    }
}
