package com.ricardo.blog.service;

import com.ricardo.blog.model.Result;
import com.ricardo.blog.model.User;
import com.ricardo.blog.model.exception.ParamException;
import com.ricardo.blog.model.param.LoginResp;

import java.util.List;

public interface UserService {

    List<User> findWith2nd(String userName, String phone) throws ParamException;

    void insertOrUpdateUser(User user,String pwd);

    LoginResp login(String userName, String pwd);

    User getCurrentUser();
}
