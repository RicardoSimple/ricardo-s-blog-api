package com.ricardo.web.api;


import com.ricardo.web.model.param.TokenInfo;
import com.ricardo.web.model.param.UserLoginRequest;
import com.ricardo.web.model.param.UserRegisterRequest;
import com.ricardo.web.service.UserService;
import com.ricardo.web.util.Code;
import com.ricardo.web.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ricardo.web.model.Result;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class LoginApi {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result registerUser(@RequestBody UserRegisterRequest param) {
        Result result = userService.registerUser(param);

        return result;
    }

    @PostMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody UserLoginRequest param) {
        if(param.getPhone().isBlank()){
            return Result.fail(Code.FAIL_DUPLICATE,"手机号为空");
        }
        Object token =  userService.login(param.getUserType(),param.getPhone(),param.getPwd());
        if (token==null){
            return Result.fail(Code.FAIL_NO_AUTH,"密码或手机号不正确");
        }
        request.getSession().setAttribute(Const.COOKIE_KEY, token);
        // 返回登录成功信息
        return Result.success(token);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Const.COOKIE_KEY);
        return Result.success(null);
    }

    @GetMapping("/user")
    public Result getCurrentUser(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(Const.COOKIE_KEY);
        if(attribute==null){
            return Result.fail(Code.FAIL_NO_AUTH,"未登录");
        }
        return Result.success(attribute);
    }
}
