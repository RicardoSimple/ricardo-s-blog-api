package com.ricardo.blog.service.impl;

import cn.hutool.core.convert.Convert;
import com.ricardo.blog.dao.UserDAO;
import com.ricardo.blog.dto.UserDO;
import com.ricardo.blog.model.Result;
import com.ricardo.blog.model.TokenInfo;
import com.ricardo.blog.model.User;
import com.ricardo.blog.model.exception.ParamException;
import com.ricardo.blog.model.param.LoginResp;
import com.ricardo.blog.service.UserService;
import com.ricardo.blog.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findWith2nd(String userName, String phone) throws ParamException {
        if(userName.isBlank()&&phone.isBlank()){
            throw new ParamException();
        }
        List<User> results = new ArrayList<>();
        if(userName.isBlank()){
            // 只查phone
            List<UserDO> userByPhone = userDAO.findUserByPhone(phone);
            for (UserDO userDO : userByPhone) {
                User user = Convert.convert(User.class, userDO);
                results.add(user);
            }
            return results;
        }else if (phone.isBlank()){
            // 只查userName
            List<UserDO> userByUserName = userDAO.findUserByUserName(userName);
            for (UserDO userDO : userByUserName) {
                User user = Convert.convert(User.class, userDO);
                results.add(user);
            }
            return results;
        }else {
            // 都查
            List<UserDO> userBy2nd = userDAO.findUserBy2nd(phone, userName);
            for (UserDO userDO : userBy2nd) {
                User user = Convert.convert(User.class, userDO);
                results.add(user);
            }
            return results;
        }
    }

    @Override
    public void insertOrUpdateUser(User user,String pwd) {
        if (user.getId()<=0){
            // 新增user
            UserDO userDO = Convert.convert(UserDO.class, user);
            userDO.setPwd(pwd);
            userDAO.insertUser(userDO);
        }else {
            // 更新user
            userDAO.updateUser(Convert.convert(UserDO.class,user));
        }
    }

    @Override
    public LoginResp login(String userName, String pwd) {
        List<UserDO> userDOS = userDAO.findUserByUserName(userName);
        if (userDOS.size() == 0){
            return null;
        }
        UserDO userDO = userDOS.get(0);
        // 加密密文
        String encrypt = PasswordUtil.encrypt(pwd, Const.PASSWORD, PasswordUtil.getStaticSalt());

        if (encrypt.equals(userDO.getPwd())){
            return new LoginResp(Convert.convert(User.class,userDO));
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        Long id = BasicContext.getCurrentId();
        UserDO user = userDAO.findUserById(id);
        return Convert.convert(User.class,user);
    }
}
