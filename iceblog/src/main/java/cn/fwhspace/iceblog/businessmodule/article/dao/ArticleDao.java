package cn.fwhspace.iceblog.businessmodule.article.dao;

import cn.fwhspace.iceblog.pojo.articles.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 13:55
 * @description 文章dao
 */
@Mapper
@Repository
public interface ArticleDao extends BaseMapper<Article> {
    /**
     * @description : 〈草稿箱文章发布〉
     * @param keyId 1
     * @return : int
     */
    @Insert("insert into article (title,description,content,author,type) select title,description,content,author,type from article_drafts where key_id = #{keyId} and deleted = 0")
    int getPulish(Integer keyId);
}
