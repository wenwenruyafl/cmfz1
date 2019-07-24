package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.dto.GuruPageDto;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;
    @Override
    public GuruPageDto queryGuruByPage(Integer page, Integer rows) {
        GuruPageDto guruPageDto = new GuruPageDto();
        //當前頁
        guruPageDto.setPage(page);
        Integer count = guruDao.selectGuruCount();
        //总页数
        guruPageDto.setTotal(count%rows==0?count/rows:count/rows+1);
        //总行数
        guruPageDto.setRecords(count);
        //当前页的数据行
        guruPageDto.setRows(guruDao.selectGuruByPage(page, rows));
        return guruPageDto;
    }
    @Transactional
    @Override
    public String addGuru(Guru guru) {
        String s= UUID.randomUUID().toString();
        guru.setId(s);
        return s;
    }
    @Transactional
    @Override
    public void deleteOneGuru(String id) {
        guruDao.deleteGuruById(id);
    }
    @Transactional
    @Override
    public String updateOneGuru(Guru guru) {
        guruDao.updateOneGuru(guru);
        return guru.getId();
    }
    @Transactional
    @Override
    public void updateFilePath(Guru guru) {
        guruDao.updateFilePath(guru);
    }

    @Override
    public List<Guru> queryAllGuru() {
        return guruDao.selectAll();
    }

    @Override
    public Guru queryOne(String id) {
        return guruDao.selectOne(id);
    }
}
