package com.ricardo.blog.service.impl;

import cn.hutool.core.convert.Convert;
import com.ricardo.blog.config.TokenConfig;
import com.ricardo.blog.dao.UserDAO;
import com.ricardo.blog.dto.UserDO;
import com.ricardo.blog.model.RefreshToken;
import com.ricardo.blog.model.User;
import com.ricardo.blog.model.param.LoginResp;
import com.ricardo.blog.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenConfig tokenConfig;

    @Autowired
    private UserDAO userDAO;
    @Override
    public LoginResp refreshToken(RefreshToken refreshToken) {
        // check secret
        if (!refreshToken.getSecret().equals(tokenConfig.secret)){
            return null;
        }
        // 更新refresh
        long id = refreshToken.getId();
        UserDO userById = userDAO.findUserById(id);
        if(userById==null){
            return null;
        }
        return new LoginResp(Convert.convert(User.class,userById));
    }
}
