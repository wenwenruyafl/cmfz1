package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.dto.ArticlePageDto;
import com.baizhi.dto.StuPageDto;
import com.baizhi.entity.Article;
import com.baizhi.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ArticleServiceImpl implements ArticleService {
  @Autowired
  private ArticleDao articleDao;
  //分页
    @Override
    public ArticlePageDto queryArticleByPage(Integer page, Integer rows) {
        ArticlePageDto articlePageDto = new ArticlePageDto();
        //當前頁
        articlePageDto.setPage(page);
        Integer count = articleDao.selectArticleCount();
        //总页数
        articlePageDto.setTotal(count%rows==0?count/rows:count/rows+1);
        //总行数
        articlePageDto.setRecords(count);
        //当前页的数据行
        articlePageDto.setRows(articleDao.selectArticleByPage(page,rows));
        return articlePageDto;
    }
//增加
    @Override
    @Transactional
    public String addArticle(Article article) {
        String id= UUID.randomUUID().toString();
        article.setId(id);
        article.setPublishTime(new Date());
        articleDao.insertOneArticle(article);
        return id;
    }
   @Transactional
    @Override
    public void deleteOneArticle(String id) {
       articleDao.deleteArticleById(id);
    }

    @Override
    public String updateOneArticle(Article article) {
        articleDao.updateOneArticle(article);
        return null;
    }

    @Override
    public void updateFilePath(Article article) {

    }

    @Override
    public Article queryOneArticle(String id) {

        return articleDao.selectOneArt(id);
    }
}
