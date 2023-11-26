package com.ricardo.web.service;

import com.ricardo.web.model.Result;
import com.ricardo.web.model.param.UserLoginRequest;
import com.ricardo.web.model.param.UserRegisterRequest;

public interface UserService {
    Result registerUser(UserRegisterRequest request);

    Result getAllUsersByType(String type);

    Object login(String userType, String phone, String pwd);

    Result getUserByIdAndType(String userType, Long id);

    Result updateUserByUserType(UserRegisterRequest params, String userType);
}
