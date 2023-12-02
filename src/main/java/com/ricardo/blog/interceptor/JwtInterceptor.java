package com.ricardo.blog.interceptor;

import com.ricardo.blog.model.TokenInfo;
import com.ricardo.blog.util.BasicContext;
import com.ricardo.blog.util.Const;
import com.ricardo.blog.util.StringUtils;
import com.ricardo.blog.util.TokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 登录拦截器

public class JwtInterceptor implements HandlerInterceptor {
    public static final Logger LOGGER = LoggerFactory.getLogger(JwtInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 只有返回true才会继续向下执行，返回false取消当前请求
        // todo 验证token
        String token = request.getHeader(Const.ATTRIBUTE_KEY);
        if(StringUtils.isNullAndBlank(token)){
            return false;
        }
        try {
            TokenInfo tokenInfo = TokenUtils.parseToken(token);
            LOGGER.info("tokenInfo :"+tokenInfo.getId());
            BasicContext.setCurrentId(tokenInfo.getId());
            return true;
        } catch (Exception e) {
            LOGGER.warn("error token from JWTInterceptor,error:",e);
            // response.sendRedirect("/login");
            return false;
        }
    }

    //Controller方法执行之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,      ModelAndView modelAndView) throws Exception {

    }

    // 整个请求完成后（包括Thymeleaf渲染完毕）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
