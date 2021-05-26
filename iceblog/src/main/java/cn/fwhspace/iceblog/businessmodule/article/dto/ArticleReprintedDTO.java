package cn.fwhspace.iceblog.businessmodule.article.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 14:14
 * @description 转载DTO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "转载DTO",description = "属性详情")
public class ArticleReprintedDTO {
    private String userId;
    private Integer articleId;
}
