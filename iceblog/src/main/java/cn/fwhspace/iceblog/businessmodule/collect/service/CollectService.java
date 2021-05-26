package cn.fwhspace.iceblog.businessmodule.collect.service;

import cn.fwhspace.iceblog.businessmodule.collect.dto.CollectDTO;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 15:27
 * @description
 */

public interface CollectService {
    /**
     * @param articleId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈添加收藏〉
     */
    GlobalOutPutDTO add(Integer articleId);

    /**
     * @param collectUserId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈获取用户收藏列表〉
     */
    GlobalOutPutDTO getList(Integer collectUserId);

    /**
     * @param collectId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈获取收藏文章详情〉
     */
    GlobalOutPutDTO getInfo(Integer collectId);

    /**
     * @param collectId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈删除收藏文章〉
     */
    GlobalOutPutDTO delete(Integer collectId);

    /**
     * @param idList 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈批量删除〉
     */
    GlobalOutPutDTO batch(List<Integer> idList);
}
