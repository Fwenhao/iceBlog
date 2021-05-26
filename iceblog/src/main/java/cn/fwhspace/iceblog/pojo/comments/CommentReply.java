package cn.fwhspace.iceblog.pojo.comments;

import cn.fwhspace.iceblog.common.enums.CommentStatus;
import cn.fwhspace.iceblog.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/11 11:51
 * @description 评论回复实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "评论回复实体类",description = "评论回复实体类描述")
public class CommentReply extends BasePojo implements Serializable {
    private static final long serialVersionUID = 4623254629074100800L;

    private Integer reviewerId;

    private Integer replierId;

    private Integer commentId;

    private String content;

    private CommentStatus status;

    private String reviewerName;

    private String replierName;

}
