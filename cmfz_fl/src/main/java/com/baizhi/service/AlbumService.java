package com.baizhi.service;


import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;

public interface AlbumService {
    AlbumPageDto queryAlbumByPage(Integer page, Integer rows);
    String addAlbum(Album album);
    void deleteOneAlbum(String id);
    String updateOneAlbum(Album album);
    void updateFilePath(Album album);
}
