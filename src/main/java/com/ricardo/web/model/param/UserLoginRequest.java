package com.ricardo.web.model.param;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String userType;

    private String phone;

    private String pwd;
}
