package com.ricardo.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    private long id;
    // 关联用户
    private long userId;
    // 关联文章
    private long articleId;
    // 关联评论 仅子评论
    private long parentId;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
}
