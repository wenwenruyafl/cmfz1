package com.baizhi.controller;

import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;
import com.baizhi.dto.StuPageDto;
import com.baizhi.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
@Slf4j
@RequestMapping("/album")
@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("queryByPage")
    @ResponseBody
    public AlbumPageDto queryByPage(Integer page, Integer rows){
        AlbumPageDto stuPageDto = albumService.queryAlbumByPage(page, rows);
        return stuPageDto;
    }

    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public String  edit(Album album, String oper){
        log.info("....{}",oper);
        if("add".equals(oper)) {
            String s = albumService.addAlbum(album);

            return  s;
        }else if("del".equals(oper)) {
            System.out.println("第一改"+album.getId());
            albumService.deleteOneAlbum(album.getId());
        }else{
            String s = albumService.updateOneAlbum(album);
            return s;
        }
        return  null;
    }
    //文件上传
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile cover, HttpServletResponse response, HttpServletRequest request, String id){
        System.out.println("//////id"+id);
        String filename = cover.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("image");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            cover.transferTo(new File(path,filename));
            //修改文件路径
            Album album = new Album();
            album.setId(id);
            album.setCover(filename);
            albumService.updateFilePath(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
