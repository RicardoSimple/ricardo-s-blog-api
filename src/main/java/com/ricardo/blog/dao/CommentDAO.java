package com.ricardo.blog.dao;

import com.ricardo.blog.dto.CommentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDAO {
    void insertComment(CommentDO commentDO);
    void updateComment(CommentDO commentDO);

    List<CommentDO> findCommentByArticleId(long articleId);
    int getCommentCountOfArticle(long articleId);

}
