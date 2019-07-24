package com.baizhi.service;

import com.baizhi.entity.Carousel;
import com.baizhi.dto.StuPageDto;

import java.util.List;

public interface CarouselService {
    List<Carousel> queryAll();
    //分頁查詢
    StuPageDto queryByPage(Integer page,Integer rows);
    String addCarousel(Carousel carousel);
    void deleteOne(String id);
    String updateOne(Carousel carousel);
    void updateFilePath(Carousel carousel);
}
