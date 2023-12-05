package com.ricardo.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = {"classpath:ricBlog.properties"})
public class GitHubConfig {

    @Value("${ricardo.blog.github.token}")
    public String githubToken;
    @Value("${ricardo.blog.github.username}")
    public String userName;
    @Value("${ricardo.blog.github.repo}")
    public String repo;
    @Value("${ricardo.blog.github.msg}")
    public String message;

}
