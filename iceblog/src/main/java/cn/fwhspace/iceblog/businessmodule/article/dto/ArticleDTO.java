package cn.fwhspace.iceblog.businessmodule.article.dto;

import cn.fwhspace.iceblog.common.enums.ArticleStatus;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 13:42
 * @description 文章CUD DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章注册DTO",description = "属性详情")
public class ArticleDTO implements Serializable {
    private static final long serialVersionUID = -7063182753335066957L;
    @NotBlank(message = "文章标题不能为空")
    private String title;
    private String description;
    @NotBlank(message = "文章内容不能为空")
    private String content;
    @NotNull(message = "文章标签不能为空")
    private List<String> tags;

    private ArticleStatus status;

    private Integer articleId;
}
