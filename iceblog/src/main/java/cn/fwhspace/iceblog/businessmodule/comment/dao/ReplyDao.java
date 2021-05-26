package cn.fwhspace.iceblog.businessmodule.comment.dao;

import cn.fwhspace.iceblog.pojo.comments.CommentReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/11 12:28
 * @description
 */

@Mapper
@Repository
public interface ReplyDao extends BaseMapper<CommentReply> {

    @Select("select * from comment_reply where replier_id = ${replierId} and deleted = 0")
    List<CommentReply> getReplysList(String replierId);

    @Update("update comment_reply set status = 1 where key_id = #{keyId} and deleted = 0")
    int updateReplyStatus(Integer keyId);
}
