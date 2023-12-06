package com.ricardo.blog.service.impl;

import cn.hutool.core.convert.Convert;
import com.ricardo.blog.dao.ArticleDAO;
import com.ricardo.blog.dao.ArticlesTagsDAO;
import com.ricardo.blog.dao.TagDAO;
import com.ricardo.blog.dto.ArticleDO;
import com.ricardo.blog.dto.TagDO;
import com.ricardo.blog.model.Article;
import com.ricardo.blog.model.Tag;
import com.ricardo.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private TagDAO tagDAO;

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

    @Override
    public List<Tag> getTagList() {
        List<TagDO> tagDOS = tagDAO.findAllTag();
        List<Tag> results = new ArrayList<>();

        for (TagDO tagDO : tagDOS) {
            results.add(Convert.convert(Tag.class,tagDO));
        }
        return results;
    }

    @Override
    public boolean addTag(String name) {
        TagDO tagByName = tagDAO.findTagByName(name);
        if (tagByName!=null){
            return false;
        }
        TagDO tagDO = new TagDO();
        tagDO.setName(name);
        tagDAO.insertTag(tagDO);
        return true;
    }

    @Override
    public Article getArticleById(long id) {
        ArticleDO articleById = articleDAO.findArticleById(id);
        if (articleById==null){
            return null;
        }
        return contactArticleAndTags(articleById);
    }

    @Override
    public List<Article> getAllArticle() {
        List<ArticleDO> allArticle = articleDAO.findAllArticle();
        List<Article> articles = new ArrayList<>();
        for (ArticleDO articleDO : allArticle) {
            articles.add(contactArticleAndTags(articleDO));
        }
        return articles;
    }

    @Override
    public List<Article> getArticleByUserId(long id) {
        List<ArticleDO> articleByUserId = articleDAO.findArticleByUserId(id);
        List<Article> results = new ArrayList<>();
        for (ArticleDO articleDO : articleByUserId) {
            results.add(contactArticleAndTags(articleDO));
        }
        return results;
    }

    private Article contactArticleAndTags(ArticleDO articleDO){
        List<TagDO> tagDOs = tagDAO.findTagsByArticleId(articleDO.getId());
        Article article = Convert.convert(Article.class, articleDO);
        List<Tag> tags = new ArrayList<>();

        for (TagDO tagDO : tagDOs) {
            tags.add(Convert.convert(Tag.class,tagDO));
        }
        article.setTags(tags);
        return article;
    }
}
