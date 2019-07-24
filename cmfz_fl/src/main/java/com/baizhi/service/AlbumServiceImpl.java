package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private AlbumDao albumDao;
    @Override
    public AlbumPageDto queryAlbumByPage(Integer page, Integer rows) {
        AlbumPageDto stuPageDto = new AlbumPageDto();
        //當前頁
        stuPageDto.setPage(page);
        Integer count = albumDao.selectAlbumCount();
        //总页数
        stuPageDto.setTotal(count%rows==0?count/rows:count/rows+1);
        //总行数
        stuPageDto.setRecords(count);
        //当前页的数据行
        stuPageDto.setRows(albumDao.selectAlbumByPage(page,rows));
        return stuPageDto;
    }
   //增加一个Album
    @Transactional
    @Override
    public String addAlbum(Album album) {
        String id= UUID.randomUUID().toString();
        album.setId(id);
        System.out.println("對象"+album);
        albumDao.insertOneAlbum(album);
        return id;
    }
//删除一个专辑
    @Transactional
    @Override
    public void deleteOneAlbum(String id) {
        albumDao.deleteAlbumById(id);
    }
//修改一个专辑
    @Transactional
    @Override
    public String updateOneAlbum(Album album) {
        albumDao.updateOneAlbum(album);
        return album.getId();
    }
//修改专辑路径
    @Transactional
    @Override
    public void updateFilePath(Album album) {
        System.out.println("serviceid"+album.getId());
        albumDao.updateFilePath(album);
    }
}
