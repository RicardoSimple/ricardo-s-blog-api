package com.ricardo.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticlesTagsDAO {
    @Insert("INSERT INTO article_tags (article_id, tag_id) VALUES (#{articleId}, #{tagId})")
    void insertArticleTag(@Param("articleId") long articleId, @Param("tagId") long tagId);

}
