package cn.fwhspace.iceblog.businessmodule.article.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/9 11:36
 * @description 文章文章更新历史记录DTO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章文章更新历史记录DTO",description = "属性详情")
public class ArticleUpdateHistoryDTO extends ArticleBaseDTO{
    /**
     * 文章更改次数
     */
    private Integer numberOfChanges;
    private Integer articleId;
}
