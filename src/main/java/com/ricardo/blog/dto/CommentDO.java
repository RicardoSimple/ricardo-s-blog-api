package com.ricardo.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDO {
    private long id;
    // 发布用户
    private long userId;
    // 关联文章
    private long articleId;
    // 顶层评论 仅子评论
    private long parentId;
    // 回复评论id 仅子评论
    private long toCommentId;
    private String content;
    private LocalDateTime gmtModified;
    private LocalDateTime gmtCreated;
}
