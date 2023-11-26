package com.ricardo.web.dto;

import com.ricardo.web.model.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDO {
    private long id;
    private String name;
    private long userID;
    private String userType;
    private String title;
    private String content;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;

    public Comment toComment(){
        Comment comment =new Comment();
        comment.setId(this.id);
        comment.setName(this.name);
        comment.setUserID(String.valueOf(this.userID));
        comment.setUserType(this.userType);
        comment.setTitle(this.title);
        comment.setContent(this.content);
        comment.setGmtCreated(this.gmtCreated);
        return comment;
    }

}
