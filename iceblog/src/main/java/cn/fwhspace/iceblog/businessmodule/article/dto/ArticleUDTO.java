package cn.fwhspace.iceblog.businessmodule.article.dto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/27 11:47
 * @description 文章更新DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章文章更新DTO",description = "属性详情")
public class ArticleUDTO extends ArticleBaseDTO{
    private static final long serialVersionUID = 8594825403928993820L;
    private Integer keyId;
    private Integer articleId;

}
