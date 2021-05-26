package cn.fwhspace.iceblog.businessmodule.article.dto;

import lombok.Data;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/9 16:36
 * @description
 */
@Data
public class ArticleBaseDTO {
    private String title;
    private String description;
    private String content;
    private String author;
    private List<String> tags;
}
