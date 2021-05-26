package cn.fwhspace.iceblog.businessmodule.article.dto;

import cn.fwhspace.iceblog.pojo.articles.Article;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleTimeLineDTO {
    private Integer count;
    private Integer year;
    private List<Article> article;
}
