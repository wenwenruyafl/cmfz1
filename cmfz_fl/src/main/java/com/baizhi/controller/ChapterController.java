package com.baizhi.controller;

import com.baizhi.dto.ChapterPageDto;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    //分页查询
    @RequestMapping("queryByPage")
    @ResponseBody
    public ChapterPageDto queryByPage(Integer page, Integer rows,String id){
        // log.info(".......{}",carouselService.queryByPage(page,rows));
        return chapterService.queryChapterByPage(page, rows, id);
    }
    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public String  edit(Chapter chapter, String oper,String idd){

        if("add".equals(oper)) {
            chapter.setAlbumId(idd);
            String s = chapterService.addChapter(chapter);
            return  s;
        }else if("del".equals(oper)) {
            chapterService.deleteOneChapter(chapter.getId());
        }else{
            String s = chapterService.updateOneChapter(chapter);
            return s;
        }
        return  null;
    }
    //文件上传
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile downPath, HttpServletResponse response, HttpServletRequest request, String id){
        String filename = downPath.getOriginalFilename();
        System.out.println("youmuyou"+filename);
        String path = request.getSession().getServletContext().getRealPath("music");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            downPath.transferTo(new File(path,filename));
            //修改文件路径
            Chapter chapter = new Chapter();
            File files = new File(path,filename);
            System.out.println("length"+files.length());
            double l = (double)files.length() / (1024 * 1024);
            BigDecimal bigDecimal = new BigDecimal(l);
            BigDecimal bigDecimal1 = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP);
            chapter.setSize(bigDecimal1.doubleValue());
            chapter.setId(id);
            chapter.setDownPath(filename);
            chapterService.updateFilePath(chapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //文件下载
    @RequestMapping("download")
    public void filedownload(String downPath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取upload目录的真实位置
        String Path = request.getSession().getServletContext().getRealPath("music");
        //获取当前文件
        System.out.println("download++++0"+downPath);
        File file = new File(Path, downPath);
        String sub = downPath.substring(downPath.lastIndexOf("."));
//        设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType(sub));
        // 设置响应头信息（以附件形式下载）
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(downPath,"utf-8"));
        //将文件响应到浏览器
        FileCopyUtils.copy(new FileInputStream(file),response.getOutputStream());
    }

}
