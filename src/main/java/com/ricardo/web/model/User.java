package com.ricardo.web.model;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;

    private String phone;

    private String nickName;

    private String avatar;

    private String email;
}
