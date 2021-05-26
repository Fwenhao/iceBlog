package cn.fwhspace.iceblog.businessmodule.comment.dao;

import cn.fwhspace.iceblog.pojo.comments.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 13:22
 * @description 评论留言dao
 */
@Mapper
@Repository
public interface CommentDao extends BaseMapper<Comment> {
    /**
     * @description : 〈获取点赞数〉
     * @param keyId 1
     * @return : java.lang.Integer
     */
    @Select("select likes from comment where key_id = #{keyId} and deleted = 0")
    Integer getLikes(Integer keyId);

    /**
     * @description : 〈获取评论〉
     * @param articleId 1
     * @return : java.util.List<cn.fwhspace.iceblog.pojo.comments.Comment>
     */
    @Select("select * from comment where article_id = #{articleId} and deleted = 0")
    List<Comment> getCommentsList(Integer articleId);

    /**
     * @description : 〈更改状态〉
     * @param keyId 1
     * @return : java.lang.Integer
     */
    @Update("update comment set status = 1 where key_id = #{keyId} and deleted = 0")
    Integer updateStatus(Integer keyId);
}
