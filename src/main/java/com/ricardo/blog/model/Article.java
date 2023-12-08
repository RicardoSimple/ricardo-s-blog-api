package com.ricardo.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Article {

    private long id;
    private long userId;
    private String title;
    private String summary;
    private boolean isHot;
    private boolean isTop;
    private String content;
    private int commentsCount;

    private int likeCount;
    private int viewCount;
    private List<Tag> tags;
    private boolean isPublished;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
}
