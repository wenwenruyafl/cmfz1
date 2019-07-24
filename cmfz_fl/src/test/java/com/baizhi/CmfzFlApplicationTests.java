package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzFlApplicationTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminDao adminDao;
    @Test
    public void contextLoads() {
        /*Admin admin = adminService.login("admin", "123456");

        System.out.println(admin);*/
        Admin admin = adminDao.selectAdmin("admin", "123456");
        System.out.println(admin);
    }

}
