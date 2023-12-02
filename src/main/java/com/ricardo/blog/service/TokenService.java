package com.ricardo.blog.service;

import com.ricardo.blog.model.RefreshToken;
import com.ricardo.blog.model.Result;
import com.ricardo.blog.model.param.LoginResp;

public interface TokenService {
    LoginResp refreshToken(RefreshToken refreshToken);
}
