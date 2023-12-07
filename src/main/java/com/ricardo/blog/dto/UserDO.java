package com.ricardo.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDO {
    private long id;
    private String phone;
    private String userName;
    private String nickName;
    private String gender;
    private String slogan;
    private String name;
    private String email;
    private String pwd;
    private String avatar;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;
}
