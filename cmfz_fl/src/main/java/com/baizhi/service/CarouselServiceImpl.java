package com.baizhi.service;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import com.baizhi.dto.StuPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;
    @Override
    public List<Carousel> queryAll() {
        return carouselDao.selectAll();
    }
//分页查询
    @Override
    public StuPageDto queryByPage(Integer page, Integer rows) {
        StuPageDto stuPageDto = new StuPageDto();
        //當前頁
        stuPageDto.setPage(page);
        Integer count = carouselDao.selectCount();
        //总页数
        stuPageDto.setTotal(count%rows==0?count/rows:count/rows+1);
        //总行数
        stuPageDto.setRecords(count);
        //当前页的数据行
        stuPageDto.setRows(carouselDao.selectByPage(page,rows));
        return stuPageDto;
    }
//增
    @Override
    @Transactional
    public String addCarousel(Carousel carousel) {
        String id=UUID.randomUUID().toString();
        carousel.setId(id);
        carouselDao.insertOne(carousel);
        return  id;
    }
//删
    @Override
    @Transactional
    public void deleteOne(String id) {
//        System.out.println("id======"+id);
        carouselDao.deleteById(id);
    }
//改
    @Override
    @Transactional
    public String updateOne(Carousel carousel) {
       // System.out.println("修改=="+carousel);
        carouselDao.updateOne(carousel);
        return carousel.getId();
    }
    //修改圖片路徑
    @Transactional
    @Override
    public void updateFilePath(Carousel carousel) {
        carouselDao.updateFilePath(carousel);
    }

}
