package com.ricardo.blog.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class RefreshToken {
    private String secret;
    private long id;
    public RefreshToken(){}
    public static RefreshToken getRefreshToken(long id,String secret){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.id = id;
        refreshToken.secret = secret;
        return refreshToken;
    }
}
