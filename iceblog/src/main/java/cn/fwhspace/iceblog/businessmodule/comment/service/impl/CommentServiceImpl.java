package cn.fwhspace.iceblog.businessmodule.comment.service.impl;

import cn.fwhspace.iceblog.businessmodule.article.dao.ArticleDao;
import cn.fwhspace.iceblog.businessmodule.comment.dao.CommentDao;
import cn.fwhspace.iceblog.businessmodule.comment.dao.ReplyDao;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentDTO;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentDeleteDTO;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentReplyDTO;
import cn.fwhspace.iceblog.businessmodule.comment.dto.CommentReplyListDTO;
import cn.fwhspace.iceblog.businessmodule.comment.service.CommentService;
import cn.fwhspace.iceblog.common.convert.IConverter;
import cn.fwhspace.iceblog.common.utils.JwtUtils;
import cn.fwhspace.iceblog.config.exception.SystemException;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.comments.Comment;
import cn.fwhspace.iceblog.pojo.comments.CommentReply;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 13:21
 * @description 评论业务实现
 */

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final IConverter iConverter;
    private final ArticleDao articleDao;
    private final JwtUtils jwtUtils;
    private final ReplyDao replyDao;
    private Integer resNumber;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, IConverter iConverter, ArticleDao articleDao, JwtUtils jwtUtils, ReplyDao replyDao) {
        this.commentDao = commentDao;
        this.iConverter = iConverter;
        this.articleDao = articleDao;
        this.jwtUtils = jwtUtils;
        this.replyDao = replyDao;
    }

    @Override
    public GlobalOutPutDTO create(CommentDTO commentDTO) {
        if (articleDao.selectById(commentDTO.getArticleId()) != null) {
            Comment comment = iConverter.convert(commentDTO, Comment.class);
            comment.setUserId(Integer.valueOf(jwtUtils.getTokenMsg("userId")));
            comment.setUserName(jwtUtils.getTokenMsg("userName"));
            resNumber = commentDao.insert(comment);
            if (resNumber == 1) {
                return GlobalOutPutDTO.success(1, "Comment on success", commentDTO);
            } else {
                throw new SystemException("评论失败，请联系管理员");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "The review articles does not exist");
        }
    }

    @Override
    public GlobalOutPutDTO addLikes(Integer keyId) {
        Comment comment = iConverter.convert(CommentDTO.builder().likes(commentDao.getLikes(keyId) + 1).keyId(keyId).build(), Comment.class);
        if (commentDao.selectById(keyId) != null) {
            resNumber = commentDao.updateById(comment);
            if (resNumber == 1) {
                return GlobalOutPutDTO.success(1, "Thumb up success", comment);
            } else {
                throw new SystemException("点赞失败，请联系管理员");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "The comments ID does not exist");
        }

    }

    @Override
    public GlobalOutPutDTO getList(Integer articleId) {
        List<Comment> commentsList = commentDao.getCommentsList(articleId);
        List<CommentReply> replyList;
        //获取该文章下所有评论的id，然后查询id对应下的回复
        List<CommentReplyListDTO> list = new ArrayList<>();


        for (int i = 0; i < commentsList.size(); i++) {
            replyList = replyDao.selectList(new QueryWrapper<CommentReply>().eq("comment_id", commentsList.get(i).getKeyId()));
            list.add(CommentReplyListDTO.builder().comment(commentsList.get(i)).replyList(replyList).build());
        }
        System.out.println(list);
        if (commentsList.size() > 0) {
            updateCommentStatus(commentsList);
            return GlobalOutPutDTO.success(1, "Comments list", list);
        } else if (commentsList.size() <= 0) {
            return GlobalOutPutDTO.fail(0, "该文章下没有任何评论！");
        } else {
            throw new SystemException("评论查询错误，请联系管理员");
        }
    }

    @Override
    public void updateCommentStatus(List<Comment> keyIdList) {
        if (keyIdList.size() > 0) {
            for (int i = 0; i < keyIdList.size(); i++) {
                commentDao.updateStatus(keyIdList.get(i).getKeyId());
            }
        }
    }

    @Override
    public GlobalOutPutDTO insertReply(CommentReplyDTO commentReplyDTO) {
        if (commentReplyDTO != null) {
            CommentReply reply = iConverter.convert(commentReplyDTO, CommentReply.class);
            reply.setReplierId(Integer.valueOf(jwtUtils.getTokenMsg("userId")));
            reply.setReplierName(jwtUtils.getTokenMsg("userName"));
            resNumber = replyDao.insert(reply);
            if (resNumber == 1) {
                return GlobalOutPutDTO.success(1, "Reply to success", commentReplyDTO);
            } else {
                throw new SystemException("评论回复失败");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "Returns the entity class as empty");
        }
    }

    @Override
    public GlobalOutPutDTO deleteReply(Integer keyId) {
        if (keyId != null) {
            resNumber = replyDao.deleteById(keyId);
            if (resNumber == 1) {
                return GlobalOutPutDTO.success(1, "Reply to success", keyId);
            } else {
                throw new SystemException("回复删除失败");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "The parameter id is null");
        }
    }

    @Override
    public GlobalOutPutDTO delete(CommentDeleteDTO commentDeleteDTO) {
        Integer userId = Integer.valueOf(jwtUtils.getTokenMsg("userId"));
        List<Comment> comments = commentDao.selectList(new QueryWrapper<Comment>().eq("article_id", commentDeleteDTO.getArticleId()));
        if (comments.size() > 0) {
            //取出评论人的id和当前token中的userId比较
            // 1,2,3,8,11,12,19
            //1，2，3，4，7，10，12
            for (int i = 0; i < comments.size(); i++) {
                if (comments.get(i).getUserId().equals(userId)) {
                    for (int j = 0; j < commentDeleteDTO.getKeyIds().size(); j++) {
                        if (commentDeleteDTO.getKeyIds().get(j).equals(comments.get(i).getKeyId())) {
                            commentDao.deleteById(commentDeleteDTO.getKeyIds().get(j));
                            //删除过后清除缓存，减少资源调度
                            commentDeleteDTO.getKeyIds().remove(j);
                            continue;
                        }
                    }
                }
            }
            return GlobalOutPutDTO.success(1, "已删除有效keyId,剩余无效的keyId", commentDeleteDTO.getKeyIds());
        } else {
            return GlobalOutPutDTO.fail(0, "ID：" + commentDeleteDTO.getArticleId() + " 文章不存在");
        }
    }

    @Override
    public void updateReplyStatus(List<CommentReply> replyList) {
        if (replyList.size() > 0) {
            for (int i = 0; i < replyList.size(); i++) {
                replyDao.updateReplyStatus(replyList.get(i).getKeyId());
            }
        }
    }

    @Override
    public GlobalOutPutDTO getReplyList(Integer replierId) {
        if (replierId != null && replyDao.selectList(new QueryWrapper<CommentReply>().eq("replier_id", replierId)).size() > 0) {
            List<CommentReply> replyList = replyDao.selectList(new QueryWrapper<CommentReply>().eq("replier_id", replierId));
            if (replyList.size() > 0) {
                updateReplyStatus(replyList);
                return GlobalOutPutDTO.success(1, "Reply message details", replyList);
            } else {
                throw new SystemException("回复信息获取失败");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "There are no reply messages");
        }
    }
}
