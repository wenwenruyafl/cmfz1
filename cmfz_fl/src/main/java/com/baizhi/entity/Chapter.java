package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    private String id;
    private String albumId;
    private String title;
    private Double  size;
    private String downPath;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date uploadTime;
}
