package cn.fwhspace.iceblog.pojo.comments;

import cn.fwhspace.iceblog.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 12:29
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "评论留言实体类",description = "评论留言实体类描述")
public class Comment extends BasePojo implements Serializable {
    private Integer articleId;

    private Integer userId;

    private String content;

    private Integer likes;

    private String userName;
}
