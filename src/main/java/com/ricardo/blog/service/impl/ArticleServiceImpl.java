package com.ricardo.blog.service.impl;

import cn.hutool.core.convert.Convert;
import com.ricardo.blog.dao.ArticleDAO;
import com.ricardo.blog.dao.ArticlesTagsDAO;
import com.ricardo.blog.dto.ArticleDO;
import com.ricardo.blog.model.Article;
import com.ricardo.blog.model.Tag;
import com.ricardo.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private ArticlesTagsDAO articlesTagsDAO;

    @Override
    public boolean addArticle(Article article) {

        // 判断参数
        if (article.getUserId()<=0){
            return false;
        }
        List<Tag> tags = article.getTags();
        for (Tag tag : tags) {
            if (tag.getId()<=0){
                return false;
            }
        }
        // todo 使用事务 防止数据错误
        // 存
        ArticleDO articleDO = Convert.convert(ArticleDO.class, article);
        articleDAO.insertArticle(articleDO);
        // 判断是否需要存Tags
        if (tags.size()>0){
            long id = articleDO.getId();
            // 存关联表
            for (Tag tag : tags) {
                articlesTagsDAO.insertArticleTag(id,tag.getId());
            }
        }
        return true;
    }
}
