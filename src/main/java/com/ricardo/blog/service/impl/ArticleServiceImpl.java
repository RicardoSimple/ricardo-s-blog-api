package com.ricardo.blog.service.impl;

import cn.hutool.core.convert.Convert;
import com.ricardo.blog.dao.ArticleDAO;
import com.ricardo.blog.dao.ArticlesTagsDAO;
import com.ricardo.blog.dao.CommentDAO;
import com.ricardo.blog.dao.TagDAO;
import com.ricardo.blog.dto.ArticleDO;
import com.ricardo.blog.dto.CommentDO;
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
    private CommentDAO commentDAO;

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
        // todo summary
        String summary = article.getContent();
        String replaceTag = summary
                .replace("<p>","").replace("</p>","")
                .replace("<h1>","").replace("</h1>","")
                .replace("<h2>","").replace("</h2>","")
                .replace("<h3>","").replace("</h3>","")
                .replace("<h4>","").replace("</h4>","")
                .replace("<h5>","").replace("</h5>","")
                .replace("<h5>","").replace("</h5>","")
                .replace("<h5>","").replace("</h5>","")
                .replace("<br>","");

        // 限制长度
        if (replaceTag.length()>200){
            replaceTag = replaceTag.substring(0,200)+"...";
        }
        // todo 使用事务 防止数据错误
        // 存
        ArticleDO articleDO = Convert.convert(ArticleDO.class, article);
        articleDO.setSummary(replaceTag);
        articleDO.setHot(false);
        articleDO.setTop(false);
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
        // 访问量
        articleDAO.incrementViewCount(id);
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

    @Override
    public List<Article> getHotArticle() {
        List<ArticleDO> articleDOS = articleDAO.findAllPublishedArticle();
        List<Article> results = new ArrayList<>();

        for (ArticleDO articleDO : articleDOS) {
            results.add(contactArticleAndTags(articleDO));
        }
        return results;
    }

    @Override
    public List<Article> getFocusArticle() {
        List<ArticleDO> allArticle = articleDAO.findAllArticle();
        List<Article> results = new ArrayList<>();

        for (ArticleDO articleDO : allArticle) {
            results.add(contactArticleAndTags(articleDO));
        }
        return results;
    }

    private Article contactArticleAndTags(ArticleDO articleDO){
        List<TagDO> tagDOs = tagDAO.findTagsByArticleId(articleDO.getId());
        Article article = Convert.convert(Article.class, articleDO);
        // 查tag
        List<Tag> tags = new ArrayList<>();

        for (TagDO tagDO : tagDOs) {
            tags.add(Convert.convert(Tag.class,tagDO));
        }
        // 查comments
        int count = commentDAO.getCommentCountOfArticle(articleDO.getId());

        article.setTags(tags);
        article.setCommentsCount(count);
        return article;
    }
}
