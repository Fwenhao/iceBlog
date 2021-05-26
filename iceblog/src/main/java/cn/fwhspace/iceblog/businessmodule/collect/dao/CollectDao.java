package cn.fwhspace.iceblog.businessmodule.collect.dao;

import cn.fwhspace.iceblog.businessmodule.collect.dto.CollectsInfoDTO;
import cn.fwhspace.iceblog.pojo.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 15:58
 * @description
 */
@Repository
@Mapper
public interface CollectDao extends BaseMapper<Collect> {
    /**
     * @description : 〈获取收藏对应文章列表〉
     * @param collectUserId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    @Select("select c.collect_user_id,a.article_id,a.article_author,a.article_content,a.article_create_time,a.article_desc,a.article_title,a.article_type " +
            "from collect c join article a " +
            "on c.collect_article_id = a.article_id where c.collect_user_id = #{collectUserId} and a.article_deleted = 0")
    List<CollectsInfoDTO> getCollectsInfo(Integer collectUserId);

    @Update("update collect set deleted = '1' where key_id = #{keyId}")
    int updateStatus(Integer keyId);
}
