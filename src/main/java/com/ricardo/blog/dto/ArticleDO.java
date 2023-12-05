package com.ricardo.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDO {
    private long id;
    private long userId;
    private String title;
    private String content;
    private int viewCount;
    private boolean isPublished;

    private LocalDateTime gmtModified;
    private LocalDateTime gmtCreated;

}
