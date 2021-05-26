package cn.fwhspace.iceblog.pojo.articles;

import cn.fwhspace.iceblog.common.enums.ArticleDraftsStatus;
import cn.fwhspace.iceblog.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 10:47
 * @description 草稿箱实体类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "草稿箱实体类",description = "草稿箱实体类描述")
public class ArticleDrafts extends BasePojo implements Serializable {
    private static final long serialVersionUID = -1469146721154402079L;

    private String title;

    private String description;

    private String content;

    private String author;

    private List<String> tags;

    private ArticleDraftsStatus status;

}
