package com.baizhi.controller;

import com.baizhi.dto.ArticlePageDto;
import com.baizhi.dto.ChapterPageDto;
import com.baizhi.entity.Article;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.Guru;
import com.baizhi.service.ArticleService;
import com.baizhi.service.ChapterService;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private GuruService guruService;
    //分页查询
    @RequestMapping("queryByPage")
    @ResponseBody
    public ArticlePageDto queryByPage(Integer page, Integer rows){
        // log.info(".......{}",carouselService.queryByPage(page,rows));
        return articleService.queryArticleByPage(page, rows);
    }
    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public String  edit(Article article, String oper){

        if("add".equals(oper)) {
            String s = articleService.addArticle(article);
            return  s;
        }else if("del".equals(oper)) {
            articleService.deleteOneArticle(article.getId());
        }else{
            String s = articleService.updateOneArticle(article);
            return s;
        }
        return  null;
    }
   // 文件上传
    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile file, HttpServletResponse response, HttpServletRequest request, String id){
        String filename = file.getOriginalFilename();
//        System.out.println("youmuyou"+filename);
        String path = request.getSession().getServletContext().getRealPath("articlePic");
        File files = new File(path);
        if(!files.exists()){
            files.mkdir();
        }
        Map<String, Object> map = new HashMap<>();
        try {
            file.transferTo(new File(path,filename));
            //修改文件路径
            map.put("error",0);
            map.put("url","http://localhost:8888/cmfz/articlePic/"+filename);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("url","http://localhost:8888/cmfz/articlePic/"+filename);
            return map;
        }
    }
//显示空间所有图片
    @RequestMapping("showAll")
    @ResponseBody
    public Map<String,Object> showAll(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String path = request.getSession().getServletContext().getRealPath("articlePic");
        File file = new File(path);
        String[] list = file.list();
        map.put("current_url","http://localhost:8888/cmfz/articlePic/");
        map.put("total_count",list.length);
        List<Object> lists=new ArrayList<>();
        for (int i=0;i<list.length;i++){
            String s=list[i];
            Map<String,Object> maps=new HashMap<>();
            maps.put("is_dir",false);
            maps.put("has_file",false);
            File file1 = new File(path,s);
            maps.put("filesize",file1.length());
            maps.put("is_photo",true);
            String substring = s.substring(s.lastIndexOf(".") + 1);
            maps.put("filetype",substring);
            maps.put("filename",s);
            maps.put("datetime",new Date());
            lists.add(maps);
        }
        map.put("file_list",lists);
        return  map;
    }
//添加文章
    @RequestMapping("addArticle")
    @ResponseBody
    public String addArticle(String title,String content,String guruName){
        Article article = new Article();
        article.setContent(content);
        article.setTitle(title);
        article.setGuruId(guruName);
        String s = articleService.addArticle(article);
        return s;
    }
    //文章浏览
    @RequestMapping("preview")
    @ResponseBody
      public Article preview(String id){
        Article article = articleService.queryOneArticle(id);
        System.out.println("2222"+article);
        Guru guru = guruService.queryOne(article.getGuruId());
        article.setGuruId(guru.getName());
        return article;
    }
}
