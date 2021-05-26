package cn.fwhspace.iceblog.pojo.articles;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/9 11:10
 * @description 博客更新记录实体类
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "博客更新记录实体类",description = "博客更新记录实体类描述")
public class ArticleUpdateHistory extends ArticleBasePojo implements Serializable {

    private static final long serialVersionUID = 6094829164198893288L;
    /**
     * 文章更改次数
     */
    private Integer numberOfChanges;
    private Integer articleId;

}
