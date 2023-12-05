package com.ricardo.blog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricardo.blog.config.GitHubConfig;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GitHubFileUploader {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/{owner}/{repo}/contents/{path}";
    public static final String IMAGE_URL_PREFIX="https://raw.githubusercontent.com/{owner}/{repo}/main/";
    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubFileUploader.class);
    public static final MediaType JSON_TYPE = MediaType.parse("application/json;charset=utf-8");

    @Autowired
    private GitHubConfig gitHubConfig;
    private final OkHttpClient client = new OkHttpClient();

    public String uploadFile(String fileName, String content) throws IOException {
        // GitHub API URL
        String apiUrl = GITHUB_API_URL
                .replace("{owner}", gitHubConfig.userName)
                .replace("{repo}", gitHubConfig.repo)
                .replace("{path}", fileName);

        // Request headers
        Headers headers = new Headers.Builder()
                .add("Authorization", "token " + gitHubConfig.githubToken)
                .add("Accept", "application/vnd.github+json")
                .build();

        // Request body as JSON
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("message", gitHubConfig.message + fileName);
        requestBodyMap.put("content", content);

        RequestBody requestBody = RequestBody.create(new ObjectMapper().writeValueAsString(requestBodyMap),JSON_TYPE);

        // Build the request
        LOGGER.info("url:" + apiUrl);
        LOGGER.info("headers:" + headers);
        LOGGER.info("requestBody:" + requestBody);
        LOGGER.info("requestBody:" + requestBody);
        Request request = new Request.Builder()
                .url(apiUrl)
                .headers(headers)
                .put(requestBody)
                .build();
        LOGGER.info("method:" + request.method());

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            LOGGER.info(String.valueOf(response));
            if (response.isSuccessful()) {
               // 返回文件链接
                return handleFileUrl(fileName);
            } else {
                return "";
            }
        }
    }
    private String handleFileUrl(String fileName){
        String url = IMAGE_URL_PREFIX
                .replace("{owner}", gitHubConfig.userName)
                .replace("{repo}", gitHubConfig.repo)
                .replace("{path}", fileName);
        return url+fileName;
    }
}
