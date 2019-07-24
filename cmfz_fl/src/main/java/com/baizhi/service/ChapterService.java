package com.baizhi.service;

import com.baizhi.dto.ChapterPageDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

public interface ChapterService {
    ChapterPageDto queryChapterByPage(Integer page, Integer rows, String id);
    String addChapter(Chapter chapter);
    void deleteOneChapter(String id);
    String updateOneChapter(Chapter chapter);
    void updateFilePath(Chapter chapter);
}
