package com.baizhi.service;

import com.baizhi.dto.UserPageDto;
import com.baizhi.entity.MouthAndCount;
import com.baizhi.entity.ProvinceAndCount;
import com.baizhi.entity.User;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface UserService {
    UserPageDto queryUserByPage(Integer page, Integer rows);
    Map<String,Object> addUser(User user);
    void deleteOneUser(String id);
    String updateOneUser(User user);
    void updateFilePath(User user);
    List<User> queryAllUser();
    Map<String,Object> queryUserLogin(String id, String password);
    Map<String,Object> queryCountAndMonth();
    Map<String , Object> queryProvinceAndCount();
}
