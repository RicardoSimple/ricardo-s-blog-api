package com.ricardo.blog.model;

import cn.hutool.core.convert.Convert;
import com.ricardo.blog.dto.UserDO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TokenInfo {
    private String userName;
    private long id;
    public static TokenInfo parseUser(User user){
        return Convert.convert(TokenInfo.class, user);
    }
}
