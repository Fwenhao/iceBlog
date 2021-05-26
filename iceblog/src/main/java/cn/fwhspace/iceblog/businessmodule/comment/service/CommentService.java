package cn.fwhspace.iceblog.businessmodule.comment.service;

import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentDTO;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentDeleteDTO;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentReplyDTO;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.comments.Comment;
import cn.fwhspace.iceblog.pojo.comments.CommentReply;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 13:21
 * @description
 */

public interface CommentService {
    /**
     * @param commentDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈评论〉
     */
    GlobalOutPutDTO create(CommentDTO commentDTO);

    /**
     * @param keyId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈点赞〉
     */
    GlobalOutPutDTO addLikes(Integer keyId);

    /**
     * @param articleId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈获取评论信息〉
     */
    GlobalOutPutDTO getList(Integer articleId);

    /**
     * @param keyId 1
     * @return : void
     * @description : 〈更新评论查看状态〉
     */
    void updateCommentStatus(List<Comment> keyId);

    /**
     * @param commentReplyDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈回复评论〉
     */
    GlobalOutPutDTO insertReply(CommentReplyDTO commentReplyDTO);

    /**
     * @param keyId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈删除评论回复〉
     */
    GlobalOutPutDTO deleteReply(Integer keyId);

    /**
     * @param commentDeleteDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈删除评论〉
     */
    GlobalOutPutDTO delete(CommentDeleteDTO commentDeleteDTO);

    /**
     * @param replyList 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈更新回复查看状态〉
     */
    void updateReplyStatus(List<CommentReply> replyList);

    /**
     * @param replierId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈获取回复信息〉
     */
    GlobalOutPutDTO getReplyList(Integer replierId);
}
