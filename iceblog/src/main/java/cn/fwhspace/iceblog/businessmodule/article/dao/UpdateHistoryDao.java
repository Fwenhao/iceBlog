package cn.fwhspace.iceblog.businessmodule.article.dao;

import cn.fwhspace.iceblog.pojo.articles.ArticleUpdateHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/9 11:31
 * @description 文章历史记录
 */
@Mapper
@Repository
public interface UpdateHistoryDao extends BaseMapper<ArticleUpdateHistory> {

    @Select("select number_of_changes from article_update_history where article_id = #{articleId}")
    List<Integer> getNumberOfChanges(Integer articleId);
}
