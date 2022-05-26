package com.zjh.myblog.controller.admin;

import com.zjh.myblog.common.AjaxResult;
import com.zjh.myblog.common.TableDataInfo;
import com.zjh.myblog.controller.BaseController;
import com.zjh.myblog.entity.BaseEntity;
import com.zjh.myblog.entity.Carousel;
import com.zjh.myblog.service.CarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description  轮播图管理
 * @auther zhengjianghai
 * @create 2022-02-18 16:05
 */
@RestController
@Api("轮播图")
@RequestMapping("system/carousel")
public class CarouselController extends BaseController {

    @Autowired
    private CarouselService carouselService;

    @PreAuthorize("@permissionService.hasPermission('system:carousel:list')")
    @GetMapping("/list")
    @ApiOperation("展示轮播图")
    public TableDataInfo list(Carousel carousel) {
        startPage();
        List<Carousel> list = carouselService.selectCarouselList(carousel);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(carouselService.selectCarouselById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:add')")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Carousel carousel) {
        return toAjax(carouselService.insertCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated(BaseEntity.Update.class) Carousel carousel) {
        return toAjax(carouselService.updateCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:edit')")
    @PutMapping("/{id}/display/{display}")
    public AjaxResult changeDisplay(@PathVariable Long id, @PathVariable Boolean display) {
        Carousel carousel = new Carousel();
        carousel.setDisplay(display);
        carousel.setId(id);
        return toAjax(carouselService.updateCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:edit')")
    @PutMapping("/{id}/target/{target}")
    public AjaxResult changeTarget(@PathVariable Long id, @PathVariable Boolean  target) {
        Carousel carousel = new Carousel();
        carousel.setTarget(target);
        carousel.setId(id);
        return toAjax(carouselService.updateCarousel(carousel));
    }

    @PreAuthorize("@permissionService.hasPermission('system:carousel:remove')")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(carouselService.deleteCarouselById(id));
    }

}


