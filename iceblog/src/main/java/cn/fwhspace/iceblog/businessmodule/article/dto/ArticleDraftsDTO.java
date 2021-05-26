package cn.fwhspace.iceblog.businessmodule.article.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 10:57
 * @description 草稿箱DTO
 */
@Data
@Builder
@ApiModel(value = "草稿箱DTO",description = "属性详情")
public class ArticleDraftsDTO {
    private String title;
    private String description;
    private String content;
    private List<String> tags;
}
