package com.ricardo.web.model.param;

import lombok.Data;

@Data
public class MessageAddParam {
    private String id;
    private String name;
    private String userID;
    private String userType;
    private String title;
    private String content;
}
