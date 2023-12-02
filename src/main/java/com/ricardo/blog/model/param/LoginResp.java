package com.ricardo.blog.model.param;

import com.ricardo.blog.model.RefreshToken;
import com.ricardo.blog.model.TokenInfo;
import com.ricardo.blog.model.User;
import lombok.Data;


@Data
public class LoginResp {
    private User currentUser;
    private String token;
    private String refreshToken;

    public LoginResp(User user){
        this.currentUser = user;
    }

}
