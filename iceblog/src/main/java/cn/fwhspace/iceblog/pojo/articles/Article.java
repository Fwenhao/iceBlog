package cn.fwhspace.iceblog.pojo.articles;

import cn.fwhspace.iceblog.common.enums.AccountIdentity;
import cn.fwhspace.iceblog.common.enums.ArticleStatus;
import cn.fwhspace.iceblog.common.handler.ListToStringHandler;
import cn.fwhspace.iceblog.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 10:57
 * @description 文章实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章实体类",description = "文章实体类描述")
@TableName(value = "article",autoResultMap = true)
public class Article extends BasePojo implements Serializable {
    private static final long serialVersionUID = -7045050017032169798L;
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
     * 文章标签
     * */

    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = ListToStringHandler.class)
    private List<String> tags;
    /**
     * 文章访问量
     * */
    private Integer vCount;
    /**
     * 文章状态   1 原创/  0 转载
     * */
    private ArticleStatus status;

    /**
     * 作者的身份
     */
    private AccountIdentity accountIdentity;

}
