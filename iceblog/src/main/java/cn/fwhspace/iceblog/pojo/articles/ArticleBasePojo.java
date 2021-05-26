package cn.fwhspace.iceblog.pojo.articles;

import cn.fwhspace.iceblog.pojo.BasePojo;
import lombok.Data;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/9 11:13
 * @description 文章类公共字段(暂无使用)
 */
@Data
public class ArticleBasePojo extends BasePojo {
    /**
     * 文章标题
     * */
    private String title;

    /**
     * 文章描述
     * */
    private String description;

    /**
     * 文章内容
     * */
    private String content;

    /**
     * 文章作者
     * */
    private String author;

    /**
     * 文章类型
     * */
    private List<String> tags;
}
