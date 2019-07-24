package com.baizhi.dao;

import com.baizhi.entity.Guru;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruDao {
    //总行数
    Integer selectGuruCount();
    //当前页的数据行
    List<Guru> selectGuruByPage(@Param("curPage") Integer curPage, @Param("pageSize") Integer pageSize);
    //增
    void insertOneGuru(Guru guru);
    //删
    void deleteGuruById(String id);
    //修改
    void updateOneGuru(Guru guru);
    //修改文件路径
    void updateFilePath(Guru guru);
    List<Guru> selectAll();
    Guru selectOne(String id);
}
