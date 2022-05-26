package com.zjh.myblog.service;

import com.zjh.myblog.entity.Carousel;
import com.zjh.myblog.mapper.CarouselMapper;
import com.zjh.myblog.utlis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @auther zhengjianghai
 * @create 2022-02-18 16:06
 */
@Service
public class CarouselService {

    @Autowired
    CarouselMapper carouselMapper;

    /**
     * 通过ID查询单条数据
     * @param id
     * @return
     */
    public Carousel selectCarouselById(Long id) {
        return carouselMapper.selectCarouselById(id);
    }

    /**
     *查询轮播图列表
     * @param carousel
     * @return
     */
    public List<Carousel> selectCarouselList(Carousel carousel) {
        return carouselMapper.selectCarouselList(carousel);
    }

    /**
     * 新增
     * @param carousel
     * @return
     */
    public int insertCarousel(Carousel carousel) {
        carousel.setCreateBy(SecurityUtils.getUsername());
        return carouselMapper.insertCarousel(carousel);
    }

    /**
     * 修改
     * @param carousel
     * @return
     */
    public int updateCarousel(Carousel carousel) {
        carousel.setUpdateBy(SecurityUtils.getUsername());
        return carouselMapper.updateCarousel(carousel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteCarouselById(Long id) {
        String username = SecurityUtils.getUsername();
        return carouselMapper.deleteCarouselById(id, username);
    }
}
