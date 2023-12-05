package com.ricardo.blog.controller;

import com.ricardo.blog.model.Article;
import com.ricardo.blog.model.Result;
import com.ricardo.blog.service.ArticleService;
import com.ricardo.blog.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public Result addArticle(@RequestBody Article article){
        boolean state = articleService.addArticle(article);
        if (!state){
            return Result.fail(Code.FAIL_ERROR_PARAM,"参数不正确");
        }
        return Result.success("添加成功");
    }
}
