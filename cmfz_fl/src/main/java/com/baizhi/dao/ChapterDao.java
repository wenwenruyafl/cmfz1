package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Carousel;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    //总行数
    Integer selectChapterCount(String id);
    //当前页的数据行
    List<Chapter> selectChapterByPage(@Param("curPage") Integer curPage, @Param("pageSize")Integer pageSize, @Param("id")String id);
    //增
    void insertOneChapter(Chapter chapter);
    //删
    void deleteChapterById(String id);
    //修改
    void updateOneChapter(Chapter chapter);
    //修改文件路径
    void updateFilePath(Chapter chapter);
}
