package com.ricardo.web.model.param;

import com.ricardo.web.model.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenInfo implements Serializable {
    public static TokenInfo getInfoByUser(User user,String userType){
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.head = user.getAvatar();
        tokenInfo.id = user.getId();
        tokenInfo.userType = userType;
        tokenInfo.nickName = user.getNickName();
        tokenInfo.phone = user.getPhone();
        return tokenInfo;
    }

    public static TokenInfo getInfoByUser(User user,String userType,long teamId){
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.head = user.getAvatar();
        tokenInfo.teamId = teamId;
        tokenInfo.id = user.getId();
        tokenInfo.userType = userType;
        tokenInfo.nickName = user.getNickName();
        tokenInfo.phone = user.getPhone();
        return tokenInfo;
    }
    private String userType;

    private String nickName;
    private long id;
    private String phone;
    private String head;
    private long teamId;
}
