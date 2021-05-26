package cn.fwhspace.iceblog.businessmodule.article.dao;

import cn.fwhspace.iceblog.pojo.articles.ArticleReprinted;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 13:41
 * @description
 */
@Mapper
@Repository
public interface ReprintedDao extends BaseMapper<ArticleReprinted> {
}
