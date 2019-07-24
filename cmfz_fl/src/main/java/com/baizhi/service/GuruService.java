package com.baizhi.service;

import com.baizhi.dto.GuruPageDto;
import com.baizhi.dto.UserPageDto;
import com.baizhi.entity.Guru;
import com.baizhi.entity.User;

import java.util.List;

public interface GuruService {
    GuruPageDto queryGuruByPage(Integer page, Integer rows);
    String addGuru(Guru guru);
    void deleteOneGuru(String id);
    String updateOneGuru(Guru guru);
    void updateFilePath(Guru guru);
    List<Guru> queryAllGuru();
    Guru queryOne(String id);
}
