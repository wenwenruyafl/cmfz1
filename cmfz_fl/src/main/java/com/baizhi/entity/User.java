package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Excel(name = "用户编号")
    private String id;
    @Excel(name="手机号")
    private String  phone;
    @Excel(name="密码")
    private String password;
    @Excel(name="盐")
    private String salt;
    @Excel(name="法名")
    private String dharmaName;
    @Excel(name="省")
    private String province;
    @Excel(name="市")
    private String  city;
    @Excel(name="性别")
    private String gender;
    @Excel(name="个性签名")
    private String personalSign;
    @Excel(name = "头像", type = 2 ,width = 40 , height = 20,imageType = 1)
    private String profile;
    @Excel(name="状态")
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="注册时间",format = "yyyy-MM-dd")
    private Date registTime;
}
