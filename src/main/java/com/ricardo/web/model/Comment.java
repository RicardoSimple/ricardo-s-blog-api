package com.ricardo.web.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private long id;
    private String name;
    private String userID;
    private String userType;
    private String title;
    private String content;
    private LocalDateTime gmtCreated;
}
