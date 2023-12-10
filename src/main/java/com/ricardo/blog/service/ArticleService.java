package com.ricardo.blog.service;

import com.ricardo.blog.model.Article;
import com.ricardo.blog.model.Tag;

import java.util.List;

public interface ArticleService {
    boolean addArticle(Article article);

    List<Tag> getTagList();

    boolean addTag(String name);

    Article getArticleById(long id);

    List<Article> getAllArticle();

    List<Article> getArticleByUserId(long id);

    List<Article> getHotArticle();
}
