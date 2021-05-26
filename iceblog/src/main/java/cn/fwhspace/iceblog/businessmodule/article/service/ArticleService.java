package cn.fwhspace.iceblog.businessmodule.article.service;

import cn.fwhspace.iceblog.businessmodule.article.dto.*;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 11:35
 * @description
 */
public interface ArticleService {

    /**
     * @param articleUDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈update〉
     */
    GlobalOutPutDTO update(ArticleUDTO articleUDTO);

    /**
     * @param id 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈delete〉
     */
    GlobalOutPutDTO delete(Integer id);

    /**
     * @param articleDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈insert〉
     */
    GlobalOutPutDTO insert(ArticleDTO articleDTO);

    /**
     * @param accessToken 1
     * @param articleId   2
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈get〉
     */
    GlobalOutPutDTO get(String accessToken, Integer articleId);

    /**
     * @param draftsDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈暂存草稿箱〉
     */
    GlobalOutPutDTO insertToDrafts(ArticleDraftsDTO draftsDTO);

    /**
     * @param draftsId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈通过获取草稿箱暂存文章〉
     */
    GlobalOutPutDTO getDraftsById(Integer draftsId);

    /**
     * @param 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈获取某作者下所有草稿箱暂存文章〉
     */
    GlobalOutPutDTO getDraftsByAuthor();

    /**
     * @param 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈草稿箱文章发布〉
     */
    GlobalOutPutDTO insertdrafts(Integer draftsId);

    /**
     * @param articleReprintedDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈文章转载〉
     */
    GlobalOutPutDTO insertReprinted(ArticleReprintedDTO articleReprintedDTO);

    /**
     * @param updateHistoryDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈文章更新历史记录〉
     */
    GlobalOutPutDTO insertUpdateHistory(ArticleUpdateHistoryDTO updateHistoryDTO, String option);

    /**
     * @param accessToken 1
     * @param articleId   2
     * @return : void
     * @description : 〈浏览记录〉
     */
    void browseRecords(String accessToken, Integer articleId);

    /**
     * @param 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈查看浏览记录〉
     */
    GlobalOutPutDTO getBrowseRecords();

    /**
     * 获取作者所有原创列表
     *
     * @param author
     * @param current
     * @param pageSize
     * @return
     */
    GlobalOutPutDTO getArticleByAuthor(String author, Integer current, Integer pageSize);

    /**
     * 获取时间轴
     *
     * @return
     */
    GlobalOutPutDTO getTimeLine();

    /**
     * 获取公告通知
     *
     * @return
     */
    GlobalOutPutDTO getNotices();
}
