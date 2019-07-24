package com.baizhi.service;

import com.baizhi.dto.ArticlePageDto;
import com.baizhi.dto.StuPageDto;
import com.baizhi.entity.Article;
import com.baizhi.entity.Carousel;

import java.util.List;

public interface ArticleService {
    //分頁查詢
    ArticlePageDto queryArticleByPage(Integer page, Integer rows);
    String addArticle(Article article);
    void deleteOneArticle(String id);
    String updateOneArticle(Article article);
    void updateFilePath(Article article);
    Article queryOneArticle(String id);
}
