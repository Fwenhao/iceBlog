package cn.fwhspace.iceblog.businessmodule.search.dao;

import cn.fwhspace.iceblog.pojo.articles.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 12:07
 * @description 检索dao
 */

@Repository
@Mapper
public interface SearchDao extends BaseMapper<Article> {

}
