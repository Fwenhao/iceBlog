package cn.fwhspace.iceblog.businessmodule.comment.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 13:33
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "评论留言DTO",description = "评论留言DTO描述")
@Builder
public class CommentDTO {
    private Integer keyId;
    private Integer articleId;
    @NotBlank(message = "评论内容不能为空")
    private String content;
    private Integer likes;
}
