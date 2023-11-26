package com.ricardo.web.service.impl;

import com.ricardo.web.dao.CommentDAO;
import com.ricardo.web.dto.CommentDO;
import com.ricardo.web.dto.TalentUserDO;
import com.ricardo.web.dto.TeamUserDO;
import com.ricardo.web.model.Comment;
import com.ricardo.web.model.Result;
import com.ricardo.web.model.TalentUser;
import com.ricardo.web.model.param.MessageAddParam;
import com.ricardo.web.service.CommentService;
import com.ricardo.web.util.Code;
import com.ricardo.web.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Override
    public Result addOrUpdateComment(MessageAddParam param) {
        CommentDO commentDO = new CommentDO();
        commentDO.setName(param.getName());
        commentDO.setContent(param.getContent());
        commentDO.setUserID(Long.parseLong(param.getUserID()));
        commentDO.setTitle(param.getTitle());
        commentDO.setUserType(param.getUserType());

        if (param.getId()==null||param.getId().isBlank()) {
            commentDAO.insertMessage(commentDO);
        }else {
            commentDO.setId(Long.parseLong(param.getId()));
            commentDAO.updateMessage(commentDO);
        }
        return Result.success(null);
    }


    @Override
    public Result GetCommentById(String Id) {
        Long id = Long.valueOf(Id);
        if (id < 0) {
            return Result.fail(Code.FAIL_NO_ZERO, "id小于0");
        }
        CommentDO commentById = commentDAO.findCommentById(id);
        return Result.success(commentById.toComment());
    }

    @Override
    public Result getCommentByUserIdAndType(String type, String id) {
        if((!type.equals(Const.TALENT_TYPE))&&(!type.equals(Const.TEAM_TYPE))){
            return Result.fail(Code.FAIL_ERROR_PARAM,"参数错误");
        }
        CommentDO commentDO = new CommentDO();
        commentDO.setUserType(type);
        commentDO.setUserID(Long.parseLong(id));
        List<CommentDO> commentDOS = commentDAO.findCommentByUserIdAndType(commentDO);
        List<Comment> results = new ArrayList<>();
        for (CommentDO aDo : commentDOS) {
            results.add(aDo.toComment());
        }
        return Result.success(results);
    }



    @Override
    public Result GetAllComment() {
        List<CommentDO> allComment = commentDAO.findAllComment();
        List<Comment> results = new ArrayList<>();
        for (CommentDO commentDO : allComment) {
            results.add(commentDO.toComment());
        }
        return Result.success(results);
    }

    @Override
    public Result deleteCommentById(String id) {
        commentDAO.deleteMessage(Long.parseLong(id));
        return Result.success(null);
    }
}
