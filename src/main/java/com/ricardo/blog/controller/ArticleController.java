package com.ricardo.blog.controller;

import com.ricardo.blog.model.Article;
import com.ricardo.blog.model.Result;
import com.ricardo.blog.service.ArticleService;
import com.ricardo.blog.util.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
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

    @PostMapping("/update")
    public Result updateArticle(@RequestBody Article article){
        boolean state = articleService.updateArticle(article);
        if (!state){
            return Result.fail(Code.FAIL_ERROR_PARAM,"参数不正确");
        }
        return Result.success("修改成功");
    }

    @GetMapping("/delete")
    public Result deleteArticle(@RequestParam long articleId){
        articleService.deleteArticleWithTag(articleId);
        return Result.success("删除成功");
    }

    @GetMapping("/get")
    public Result getArticleById(@RequestParam("id") long id){
        if (id<=0){
            return Result.fail(Code.FAIL_ERROR_PARAM,"wrong id");
        }
        Article article = articleService.getArticleById(id);
        if (article==null){
            return Result.fail(Code.FAIL_ERROR_PARAM,"wrong id");
        }
        return Result.success(article);
    }

    @GetMapping("/all")
    public Result getAllArticle(){
        List<Article> articles = articleService.getAllArticle();

        return Result.success(articles);
    }

    @GetMapping("/user")
    public Result getArticleUnderUser(@RequestParam("userId") long id){
        List<Article> articles = articleService.getArticleByUserId(id);
        return Result.success(articles);
    }

    @GetMapping("/hot")
    public Result getHotArticle(){
        List<Article> results = articleService.getHotArticle();
        return Result.success(results);
    }

    @GetMapping("/focus")
    public Result getFocusArticle(){
        List<Article> results = articleService.getFocusArticle();
        return Result.success(results);
    }
}
