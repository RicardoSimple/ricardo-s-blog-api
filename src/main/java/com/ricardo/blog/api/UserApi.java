package com.ricardo.blog.api;

import cn.hutool.core.convert.Convert;
import com.ricardo.blog.config.TokenConfig;
import com.ricardo.blog.model.RefreshToken;
import com.ricardo.blog.model.Result;
import com.ricardo.blog.model.TokenInfo;
import com.ricardo.blog.model.User;
import com.ricardo.blog.model.exception.ParamException;
import com.ricardo.blog.model.param.LoginParam;
import com.ricardo.blog.model.param.LoginResp;
import com.ricardo.blog.model.param.RegisterParam;
import com.ricardo.blog.service.TokenService;
import com.ricardo.blog.service.UserService;
import com.ricardo.blog.util.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private TokenConfig tokenConfig;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterParam param, HttpServletRequest request){
        if(StringUtils.isNullAndBlank(param.getUserName())||StringUtils.isNullAndBlank(param.getPhone())){
            return Result.fail(Code.FAIL_ERROR_PARAM,"手机号或用户名为空");
        }
        // 查重
        try {
            List<User> users = userService.findWith2nd(param.getUserName(),param.getPhone());
            if(users.size()>0){
                return Result.fail(Code.FAIL_DUPLICATE,"该手机号或用户名已注册");
            }
        } catch (ParamException e) {
            throw new RuntimeException(e);
        }
        // todo 校验email,phone,url的格式
        // 注册逻辑
        // 加密
        String encrypt = PasswordUtil.encrypt(param.getPwd(), Const.PASSWORD, PasswordUtil.getStaticSalt());
        userService.insertOrUpdateUser(Convert.convert(User.class,param),encrypt);
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginParam param, HttpServletRequest request, HttpServletResponse response) throws ParamException {
        if (StringUtils.isNullAndBlank(param.getUserName())||StringUtils.isNullAndBlank(param.getPwd())){
            return Result.fail(Code.FAIL_ERROR_PARAM,"用户名或密码为空");
        }
        LoginResp loginResp = userService.login(param.getUserName(), param.getPwd());
        if (loginResp==null){
            return Result.fail(Code.FAIL_NO_AUTH,"用户名或密码错误");
        }
        LoginResp resp = fullToken(loginResp);
        response.setHeader(Const.ATTRIBUTE_KEY,resp.getToken());
        return Result.success(loginResp);
    }

    // 请求头中包含refreshToken
    @GetMapping("/refresh")
    public Result refreshToken(HttpServletRequest request,HttpServletResponse response){
        String refreshTokenStr = request.getHeader(Const.REFRESH_TOKEN_KEY);
        try {
            // 解析token
            RefreshToken tokenInfo = TokenUtils.parseRefreshToken(refreshTokenStr);
            LoginResp loginResp = tokenService.refreshToken(tokenInfo);
            if (loginResp==null){
                return Result.fail(Code.FAIL_NO_AUTH,"token不正确");
            }
            LoginResp resp = fullToken(loginResp);
            response.setHeader(Const.ATTRIBUTE_KEY,resp.getToken());
            return Result.success(loginResp);
        }catch (ExpiredJwtException exception){
            LOGGER.warn("token已过期，转换失败，error:",exception);
            return Result.fail(Code.FAIL_NO_AUTH,"token不正确或已过期");
        }
    }

    // 请求头中包含token
    @GetMapping("/check")
    public Result checkToken(HttpServletRequest request,HttpServletResponse response){
        String token = request.getHeader(Const.ATTRIBUTE_KEY);
        try {
            TokenInfo tokenInfo = TokenUtils.parseToken(token);
            // 过期时间
            response.setHeader(Const.ATTRIBUTE_KEY,token);
            return Result.success(token);
        } catch (Exception e) {
            LOGGER.warn("token已过期，转换失败，error:",e);
            return Result.fail(Code.FAIL_NO_AUTH,"token不正确或已过期");
        }
    }

    @GetMapping("/currentUser")
    public Result currentUser(HttpServletRequest request){
        User user = userService.getCurrentUser();
        return Result.success(user);
    }
    private LoginResp fullToken(LoginResp loginResp){
        String token = TokenUtils.generateToken(TokenInfo.parseUser(loginResp.getCurrentUser()));
        String refreshToken = TokenUtils.generateRefreshToken(RefreshToken.getRefreshToken(
                loginResp.getCurrentUser().getId(),tokenConfig.secret

        ));
        // 存用户信息
        loginResp.setToken(token);
        loginResp.setRefreshToken(refreshToken);
        // 请求头加入token
        LOGGER.info("token: "+token);
        // 后续部分请求需要在header中携带token，refreshToken用于刷新获取token
        return loginResp;
    }
}
