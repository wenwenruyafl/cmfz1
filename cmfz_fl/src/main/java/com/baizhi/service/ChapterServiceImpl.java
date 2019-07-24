package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.dto.ChapterPageDto;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    //分页
    @Override
    public ChapterPageDto queryChapterByPage(Integer page, Integer rows, String id) {
        ChapterPageDto chapterPageDto = new ChapterPageDto();
        //當前頁
        chapterPageDto.setPage(page);
        Integer count = chapterDao.selectChapterCount(id);
        //总页数
        chapterPageDto.setTotal(count%rows==0?count/rows:count/rows+1);
        //总行数
        chapterPageDto.setRecords(count);
        //当前页的数据行
        chapterPageDto.setRows(chapterDao.selectChapterByPage(page, rows, id));
        return chapterPageDto;
    }
    //增加
    @Transactional
    @Override
    public String addChapter(Chapter chapter) {
        String s=UUID.randomUUID().toString();
        chapter.setId(s);
        chapterDao.insertOneChapter(chapter);
        return s;
    }
//删除
    @Override
    public void deleteOneChapter(String id) {
        chapterDao.deleteChapterById(id);
    }
//修改
    @Override
    public String updateOneChapter(Chapter chapter) {
        chapterDao.updateOneChapter(chapter);
        return chapter.getId();
    }
//修改路径
    @Override
    public void updateFilePath(Chapter chapter) {
        chapterDao.updateFilePath(chapter);
    }
}
