package cn.fwhspace.iceblog.pojo.articles;

import cn.fwhspace.iceblog.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/9 13:54
 * @description
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章浏览记录实体类",description = "文章浏览记录实体类描述")
public class ArticleBrowseHistory extends BasePojo {
    private Integer articleId;
    private String viewer;
}
