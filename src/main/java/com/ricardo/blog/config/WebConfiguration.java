package com.ricardo.blog.config;

import com.ricardo.blog.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 拦截器配置
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor()).excludePathPatterns(
                "/api/user/login",
                "/api/user/register",
                "/api/user/check",
                "/api/tag/list");
    }
}
