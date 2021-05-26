package cn.fwhspace.iceblog.businessmodule.search.service;

import cn.fwhspace.iceblog.common.enums.AccountIdentity;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 12:06
 * @description 检索接口
 */
public interface SearchService {
    /**
     * @param content 1
     * @param option  2
     * @param pages   3
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈模糊匹配〉
     */
    GlobalOutPutDTO getArticlesListOrderByDesc(String content, String option, Integer pages);

    /**
     * @param 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈查询推荐文章〉
     */
    GlobalOutPutDTO getArticlesListByRound();

    /**
     * 查询详情
     *
     * @param articleName
     * @param current
     * @param pageSize
     * @return
     */
    GlobalOutPutDTO getArticleByName(String articleName, Integer current, Integer pageSize, AccountIdentity accountIdentity);

}
