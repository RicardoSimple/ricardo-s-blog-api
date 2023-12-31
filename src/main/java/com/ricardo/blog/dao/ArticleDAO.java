package com.ricardo.blog.dao;

import com.ricardo.blog.dto.ArticleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDAO {
    void insertArticle(ArticleDO articleDO);
    void updateArticle(ArticleDO articleDO);

    List<ArticleDO> findArticleByUserId(long userId);
    ArticleDO findArticleById(long id);

    List<ArticleDO> findAllArticle();
    List<ArticleDO> findAllPublishedArticle();
    int deleteArticle(long id);
    void incrementViewCount(long id);  // 新添加的方法
    void incrementLikeCount(long id);  // 新添加的方法
}
