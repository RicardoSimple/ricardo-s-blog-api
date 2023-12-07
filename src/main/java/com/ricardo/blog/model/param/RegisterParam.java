package com.ricardo.blog.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterParam {
    private String userName;
    private String phone;
    private String nickName;
    private String gender;
    private String name;
    private String email;
    private String pwd;
    // private String avatar;
}
