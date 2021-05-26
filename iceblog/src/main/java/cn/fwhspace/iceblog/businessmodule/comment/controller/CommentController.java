package cn.fwhspace.iceblog.businessmodule.comment.controller;

import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentDTO;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentDeleteDTO;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentReplyDTO;
import cn.fwhspace.iceblog.businessmodule.comment.service.CommentService;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 12:27
 * @description 评论业务controller
 */
@RequestMapping("/comment")
@Api(tags = "评论业务操作接口", description = "评论留言管理")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "创建评论")
    public GlobalOutPutDTO createComment(@RequestBody @Valid CommentDTO commentDTO, @RequestHeader("token") String token) {
        return commentService.create(commentDTO);
    }

    @PutMapping("/addlikes")
    @ApiOperation(value = "评论点赞")
    public GlobalOutPutDTO addCommentLikes(Integer keyId, @RequestHeader("token") String token) {
        return commentService.addLikes(keyId);
    }

    @GetMapping("/getlist")
    @ApiOperation(value = "获取评论")
    public GlobalOutPutDTO getCommentsList(Integer articleId) {
        return commentService.getList(articleId);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除评论")
    public GlobalOutPutDTO deleteCommentsList(@RequestBody CommentDeleteDTO commentDeleteDTO, @RequestHeader("token") String token) {
        return commentService.delete(commentDeleteDTO);
    }


    @PostMapping("/reply/insert")
    @ApiOperation(value = "回复评论", notes = "查看回复的记录")
    public GlobalOutPutDTO insertToReply(@RequestBody CommentReplyDTO commentReplyDTO, @RequestHeader("token") String token) {
        return commentService.insertReply(commentReplyDTO);
    }

    @DeleteMapping("/reply/delete")
    @ApiOperation(value = "删除评论回复")
    public GlobalOutPutDTO deleteReplyById(Integer keyId, @RequestHeader("token") String token) {
        return commentService.deleteReply(keyId);
    }

    @GetMapping("/reply/get")
    @ApiOperation(value = "获取回复")
    public GlobalOutPutDTO getReplyList(Integer replierId) {
        return commentService.getReplyList(replierId);
    }

}
