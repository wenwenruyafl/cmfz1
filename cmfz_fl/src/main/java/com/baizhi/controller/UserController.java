package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dto.UserPageDto;
import com.baizhi.entity.MouthAndCount;
import com.baizhi.entity.ProvinceAndCount;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.google.gson.Gson;
import io.goeasy.GoEasy;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //分页查询
    @RequestMapping("queryByPage")
    @ResponseBody
    public UserPageDto queryByPage(Integer page, Integer rows){
        // log.info(".......{}",carouselService.queryByPage(page,rows));
        return userService.queryUserByPage(page, rows);
    }
//    //增删改
//    @RequestMapping("edit")
//    @ResponseBody
//    public String  edit(User user, String oper){
//
//        if("edit".equals(oper)) {
//            String s = userService.updateOneUser(user);
//            return  s;
//        }else if("del".equals(oper)) {
//            userService.deleteOneUser(user.getId());
//        }
//        return  null;
//    }
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
            User user = new User();
            user.setId(id);
            user.setProfile(filename);
            userService.updateFilePath(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("exportUser")
    public void ExportUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
        List<User> users = userService.queryAllUser();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("所有用户","用户"),
                User.class, users);

        //        设置响应类型
//        response.setContentType(request.getSession().getServletContext().getMimeType(".xml"));
        // 设置响应头信息（以附件形式下载）
        response.setHeader("content-disposition","attachment;filename=use.xml");
        response.setHeader("content-Type","application/vnd.ms-excel");
        //将文件响应到浏览器
//        FileCopyUtils.copy(new FileInputStream(file),response.getOutputStream());
        workbook.write(response.getOutputStream());
        workbook.close();
    }
    @ResponseBody
    @RequestMapping("importUser")
    public void importUser(MultipartFile file) throws IOException {
        //创建一个workbook接受
        Workbook workbook=new HSSFWorkbook(file.getInputStream());
        //获取第一个表
        Sheet sheetAt = workbook.getSheetAt(0);
        //获取一共有多少航
        int lastRowNum = sheetAt.getLastRowNum();
        //创建一个集合去接受
        List<User> users=new ArrayList<>();
        for(int i=0;i<=lastRowNum-2;i++){
            User user=new User();
            Row row = sheetAt.getRow(i+2);
            Cell cell1 = row.getCell(0);
            String id = cell1.getStringCellValue();
            user.setId(id);
            Cell cell2 = row.getCell(1);
            user.setPhone(cell2.getStringCellValue());
            Cell cell3 = row.getCell(2);
            user.setPassword(cell3.getStringCellValue());
            Cell cell4 = row.getCell(3);
            user.setSalt(cell4.getStringCellValue());
            Cell cell5 = row.getCell(4);
            user.setDharmaName(cell5.getStringCellValue());
            Cell cell6 = row.getCell(5);
            user.setProvince(cell6.getStringCellValue());
            Cell cell7 = row.getCell(6);
            user.setCity(cell7.getStringCellValue());
            Cell cell8 = row.getCell(7);
            user.setGender(cell8.getStringCellValue());
            Cell cell9 = row.getCell(8);
            user.setPersonalSign(cell9.getStringCellValue());
            Cell cell10 = row.getCell(9);
            user.setProfile(cell10.getStringCellValue());
            Cell cell11 = row.getCell(10);
            user.setStatus(cell11.getStringCellValue());
            Cell cell12 = row.getCell(11);
            user.setRegistTime(cell12.getDateCellValue());
            users.add(user);
        }
        System.out.println("users==="+users);
        for (User user : users) {
            userService.addUser(user);
        }
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(String userId, String password){
        Map<String, Object> map = userService.queryUserLogin(userId, password);
        return map;

    }
    //注册
    @RequestMapping("register")
    @ResponseBody
    public Map<String, Object> register(User user){
        Map<String, Object> map = userService.addUser(user);
        Set<String> strings = map.keySet();
        for (String string : strings) {
            if("code".equals(string)){
                Map<String, Object> map1 = userService.queryCountAndMonth();
                Map<String, Object> map2 = userService.queryProvinceAndCount();
                Gson gson = new Gson();
                String s = gson.toJson(map1);
                String s1 = gson.toJson(map2);
                GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-155e769e1ce54593a386d1e2308ca77b");
                goEasy.publish("demo_channel",s);
                goEasy.publish("demo_channe2",s1);
            }
        }
        return map;
    }
    //查相对应月份注册人数，柱状图
    @RequestMapping("histogram")
    @ResponseBody
    public Map<String, Object> histogram(){
        Map<String, Object> mouthAndCounts = userService.queryCountAndMonth();
        System.out.println("===="+mouthAndCounts);
        return mouthAndCounts;
    }
    //查不同性别注册的省份，地图
    @RequestMapping("mapEcharts")
    @ResponseBody
    public Map<String , Object> mapEcharts(){
        Map<String , Object> stringListMap = userService.queryProvinceAndCount();
        return stringListMap;
    }
}
