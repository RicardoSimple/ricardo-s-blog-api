package com.ricardo.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDO {
    private long id;
    private long userId;
    private long articleId;
    private long parentId;
    private String content;
    private LocalDateTime gmtModified;
    private LocalDateTime gmtCreated;
}
