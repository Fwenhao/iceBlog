package cn.fwhspace.iceblog.businessmodule.article.dao;

import cn.fwhspace.iceblog.pojo.articles.ArticleDrafts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 11:04
 * @description 草稿箱dao
 */
@Mapper
@Repository
public interface DraftsDao extends BaseMapper<ArticleDrafts> {

    /**
     * @description : 〈更新草稿箱文章状态〉
     * @param keyId 1
     * @return : int
     */
    @Update("update article_drafts set status = 1 where key_id = #{keyId}")
    int updateStatus(Integer keyId);
}
