package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;

import java.util.List;

public interface AlbumDao {
    //总行数
    Integer selectAlbumCount();
    //当前页的数据行
    List<Album> selectAlbumByPage(Integer curPage, Integer pageSize);
    //增
    void insertOneAlbum(Album album);
    //删
    void deleteAlbumById(String id);
    //修改
    void updateOneAlbum(Album album);
    //修改文件路径
    void updateFilePath(Album album);
}
