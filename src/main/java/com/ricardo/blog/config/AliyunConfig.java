package com.ricardo.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = {"classpath:ricBlog.properties"})
public class AliyunConfig {

    @Value("${ricardo.blog.aliyun.accessKeyId}")
    private String accessTokenId;
    @Value("${ricardo.blog.aliyun.accessKeySecret}")
    private String accessTokenSecret;
}
