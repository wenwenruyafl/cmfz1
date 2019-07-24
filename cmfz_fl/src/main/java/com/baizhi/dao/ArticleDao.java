package com.baizhi.dao;

import com.baizhi.entity.Article;
import com.baizhi.entity.Carousel;

import java.util.List;

public interface ArticleDao {
    //总行数
    Integer selectArticleCount();
    //当前页的数据行
    List<Article> selectArticleByPage(Integer curPage, Integer pageSize);
    //增
    void insertOneArticle(Article article);
    //删
    void deleteArticleById(String id);
    //修改
    void updateOneArticle(Article article);
    //修改文件路径
    void updateFilePath(Article Article);
    //查询一片文章
    Article selectOneArt(String id);
}
