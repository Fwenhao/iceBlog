package cn.fwhspace.iceblog.businessmodule.article.dao;

import cn.fwhspace.iceblog.pojo.articles.ArticleBrowseHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/9 13:57
 * @description
 */
@Mapper
@Repository
public interface BrowseHistoryDao extends BaseMapper<ArticleBrowseHistory> {
}
