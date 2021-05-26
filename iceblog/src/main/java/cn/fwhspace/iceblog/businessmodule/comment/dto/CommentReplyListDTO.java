package cn.fwhspace.iceblog.businessmodule.comment.dto;

import cn.fwhspace.iceblog.pojo.comments.Comment;
import cn.fwhspace.iceblog.pojo.comments.CommentReply;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentReplyListDTO {
    private Comment comment;
    private List<CommentReply> replyList;
}
