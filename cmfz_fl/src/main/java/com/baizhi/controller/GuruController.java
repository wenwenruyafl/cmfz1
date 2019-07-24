package com.baizhi.controller;

import com.baizhi.dto.GuruPageDto;
import com.baizhi.dto.UserPageDto;
import com.baizhi.entity.Guru;
import com.baizhi.entity.User;
import com.baizhi.service.GuruService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    //查所有
    @RequestMapping("queryAllGuru")
    @ResponseBody
    public List<Guru> queryAllGuru(){
        List<Guru> gurus = guruService.queryAllGuru();
        System.out.println("gugug=="+gurus);
        return gurus;
    }
    //分页查询
    @RequestMapping("queryByPage")
    @ResponseBody
    public GuruPageDto queryByPage(Integer page, Integer rows){
        // log.info(".......{}",carouselService.queryByPage(page,rows));
        return guruService.queryGuruByPage(page, rows);
    }
    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public String  edit(Guru guru, String oper){
        if("add".equals(oper)) {
            String s = guruService.addGuru(guru);
            return  s;
        }else if("del".equals(oper)) {
            guruService.deleteOneGuru(guru.getId());
        }else{
            String s = guruService.updateOneGuru(guru);
            return s;
        }
        return  null;
    }
    //文件上传
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile profile, HttpServletResponse response, HttpServletRequest request,String id){
        String filename = profile.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("image");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            profile.transferTo(new File(path,filename));
            //修改文件路径
            Guru guru = new Guru();
            guru.setId(id);
            guru.setProfile(filename);
            guruService.updateFilePath(guru);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
