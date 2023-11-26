package com.ricardo.web.api;

import com.ricardo.web.model.Result;
import com.ricardo.web.model.param.UserRegisterRequest;
import com.ricardo.web.service.UserService;
import com.ricardo.web.util.Code;
import com.ricardo.web.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Result getAllUsers(@RequestParam("type")String type){
     return userService.getAllUsersByType(type);
    }

    @GetMapping("/get")
    public Result getUserWithIdAndType(@RequestParam("id")String id,@RequestParam("type")String type){

        if(!(type.equals(Const.TALENT_TYPE)||type.equals(Const.TEAM_TYPE))){
            return Result.fail(Code.FAIL_ERROR_PARAM,"用户类型错误");
        }
        return userService.getUserByIdAndType(type,Long.valueOf(id));
    }
    @PostMapping("/update")
    public Result updateUserByUserType(@RequestBody UserRegisterRequest params,@RequestParam("type")String userType){
        if(params.getId()==null||params.getId().isBlank()){
            return Result.fail(Code.FAIL_ERROR_PARAM,"无用户id");
        }
        return userService.updateUserByUserType(params,userType);
    }

}
