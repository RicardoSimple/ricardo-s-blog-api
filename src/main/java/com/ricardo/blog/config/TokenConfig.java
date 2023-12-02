package com.ricardo.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = {"classpath:ricBlog.properties"})
public class TokenConfig {

    @Value("${ricardo.blog.token.secret}")
    public String secret;
}
