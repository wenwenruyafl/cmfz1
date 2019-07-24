package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import com.baizhi.entity.MouthAndCount;
import com.baizhi.entity.ProvinceAndCount;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //总行数
    Integer selectUserCount();
    //当前页的数据行
    List<User> selectUserByPage(@Param("curPage") Integer curPage, @Param("pageSize") Integer pageSize);
    //增
    void insertOneUser(User user);
    //删
    void deleteUserById(String id);
    //修改
    void updateOneUser(User user);
    //修改文件路径
    void updateFilePath(User user);
    //查所有
    List<User> selectAllUser();
    //查一个
    User selectOne(String phone);
    //查某月的注册人数
    List<MouthAndCount> selectMouthAndCount();
    //男女省份的人数
    List<ProvinceAndCount> selectProvinceAndCount(String province);
}
