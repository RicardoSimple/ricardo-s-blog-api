package com.ricardo.blog.controller;

import com.ricardo.blog.model.Result;
import com.ricardo.blog.model.Tag;
import com.ricardo.blog.service.ArticleService;
import com.ricardo.blog.util.Code;
import com.ricardo.blog.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private ArticleService articleService;
    @GetMapping("/list")
    public Result getTagList(){
        List<Tag> tags = articleService.getTagList();
        return Result.success(tags);
    }

    @PostMapping("/add")
    public Result addTag(@RequestParam("name") String name){
        if (StringUtils.isNullAndBlank(name)){
            return Result.fail(Code.FAIL_ERROR_PARAM,"参数为空");
        }
        boolean result = articleService.addTag(name);
        if (result) {
            return Result.success("添加成功");
        }
        return Result.fail(Code.FAIL_ERROR_PARAM,"标签名重复");
    }
}
