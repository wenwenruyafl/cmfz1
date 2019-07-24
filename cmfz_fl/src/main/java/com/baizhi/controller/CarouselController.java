package com.baizhi.controller;

import com.baizhi.entity.Carousel;
import com.baizhi.dto.StuPageDto;
import com.baizhi.service.CarouselService;
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
import java.util.List;
@Controller
@RequestMapping("/carousel")
@Slf4j
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @RequestMapping("queryAll")
    @ResponseBody
    public List<Carousel> queryAllCarousel(){
        //log.info(".......");
        List<Carousel> carousels = carouselService.queryAll();
        //log.info("{}",carousels);
        return carousels;
    }
    //分页查询
    @RequestMapping("queryByPage")
    @ResponseBody
    public StuPageDto queryByPage(Integer page,Integer rows){
       // log.info(".......{}",carouselService.queryByPage(page,rows));
        return carouselService.queryByPage(page,rows);
    }

    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public String  edit(Carousel carousel,String oper){
        log.info("....{}",oper);
        if("add".equals(oper)) {
            String s = carouselService.addCarousel(carousel);

            return  s;
        }else if("del".equals(oper)) {
//            System.out.println("第一改"+carousel.getId());
            carouselService.deleteOne(carousel.getId());
        }else{
            String s = carouselService.updateOne(carousel);
            return s;
        }
         return  null;
    }
//文件上传
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile imgPath, HttpServletResponse response, HttpServletRequest request, String id){

        String filename = imgPath.getOriginalFilename();
        //System.out.println(filename);
        String path = request.getSession().getServletContext().getRealPath("image");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            imgPath.transferTo(new File(path,filename));
            //修改文件路径
            Carousel carousel = new Carousel();
            carousel.setId(id);
            carousel.setImgPath(filename);
            carouselService.updateFilePath(carousel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
