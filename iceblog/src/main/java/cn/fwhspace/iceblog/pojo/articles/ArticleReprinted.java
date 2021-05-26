package cn.fwhspace.iceblog.pojo.articles;

import cn.fwhspace.iceblog.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 13:39
 * @description 文章转载
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章转载实体类",description = "文章转载实体类描述")
public class ArticleReprinted extends BasePojo implements Serializable {
    private String userId;
    private String author;
    private Integer articleId;
}
