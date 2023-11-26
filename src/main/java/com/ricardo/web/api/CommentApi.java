package com.ricardo.web.api;

import com.ricardo.web.model.Result;
import com.ricardo.web.model.param.MessageAddParam;
import com.ricardo.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentApi {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Result addOrUpdateMessage(@RequestBody MessageAddParam param){
        return commentService.addOrUpdateComment(param);
    }


    @GetMapping("/getbyid")
    public Result GetCommentById(@RequestParam("id") String id){
        return commentService.GetCommentById(id);
    }

    @GetMapping("/userIdAndType")
    public Result GetCommentByIdAndType(@RequestParam("type")String type,@RequestParam("userId") String id){
        return commentService.getCommentByUserIdAndType(type,id);
    }

    @GetMapping("/all")
    public Result getAllComment(){
        return commentService.GetAllComment();
    }

    @DeleteMapping("/delete")
    public Result deleteCommentById(@RequestParam("id")String id){
        return commentService.deleteCommentById(id);
    }
}
