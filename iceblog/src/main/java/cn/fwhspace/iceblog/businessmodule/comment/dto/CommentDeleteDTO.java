package cn.fwhspace.iceblog.businessmodule.comment.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/12 17:38
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除评论DTO",description = "删除评论DTO描述")
@Builder
public class CommentDeleteDTO {
    private Integer articleId;
    private List<Integer> keyIds;
}
