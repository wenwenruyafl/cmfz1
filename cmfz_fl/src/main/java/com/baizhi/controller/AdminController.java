package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Carousel;
import com.baizhi.service.AdminService;
import com.baizhi.service.CarouselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cmfz")
@Slf4j
public class AdminController {
   @Autowired
   private AdminService adminService;

   @RequestMapping( "admin")
   private String adminLooin(Admin admin, Model model){
       Admin login = adminService.login(admin.getUsername(), admin.getPassword());
       log.info("this...{}",login);
       if(login!=null){
           model.addAttribute("username",login.getUsername());
           return "jsp/main";
       }
       return "jsp/admin";
   }

}
