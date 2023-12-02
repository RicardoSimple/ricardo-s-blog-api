package com.ricardo.blog.dao;

import com.ricardo.blog.dto.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {
    void insertUser(UserDO userDO);

    void updateUser(UserDO userDO);

    UserDO findUserById(long id);
    List<UserDO> findUserByUserName(String userName);
    List<UserDO> findUserByPhone(String phone);
    List<UserDO> findUserBy2nd(@Param("phone") String phone, @Param("userName") String userName);


    void deleteUser(long id);
}
