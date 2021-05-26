package cn.fwhspace.iceblog.businessmodule.comment.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/11 12:00
 * @description 评论回复DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "评论回复DTO",description = "评论回复DTO描述")
@Builder
public class CommentReplyDTO {
    private Integer keyId;

    private Integer reviewerId;

    private Integer commentId;

    @NotNull(message = "回复内容不能为空")
    private String content;

    private String reviewerName;
}
