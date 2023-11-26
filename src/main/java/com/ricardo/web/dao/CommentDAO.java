package com.ricardo.web.dao;

import com.ricardo.web.dto.CommentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDAO {
    List<CommentDO> findAllComment();

    void insertMessage(CommentDO commentDO);
    void updateMessage(CommentDO commentDO);

    CommentDO findCommentById(long id);

    List<CommentDO> findCommentByUserIdAndType(CommentDO commentDO);

    void deleteMessage(long Id);

    List<CommentDO> findMessageByTeamId(Long teamId);

}
