package com.baizhi.dao;

import com.baizhi.entity.Carousel;

import java.util.List;

public interface CarouselDao {
    List<Carousel> selectAll();
    //总行数
    Integer selectCount();
    //当前页的数据行
    List<Carousel> selectByPage(Integer curPage,Integer pageSize);
    //增
    void insertOne(Carousel carousel);
    //删
    void deleteById(String id);
    //修改
    void updateOne(Carousel carousel);
    //修改文件路径
    void updateFilePath(Carousel carousel);
}
