package cn.fwhspace.iceblog.businessmodule.collect.service.impl;

import cn.fwhspace.iceblog.businessmodule.article.dao.ArticleDao;
import cn.fwhspace.iceblog.businessmodule.collect.dao.CollectDao;
import cn.fwhspace.iceblog.businessmodule.collect.dto.CollectDTO;
import cn.fwhspace.iceblog.businessmodule.collect.service.CollectService;
import cn.fwhspace.iceblog.common.convert.IConverter;
import cn.fwhspace.iceblog.common.utils.JwtUtils;
import cn.fwhspace.iceblog.config.exception.SystemException;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.Collect;
import cn.fwhspace.iceblog.pojo.articles.Article;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 15:54
 * @description
 */

@Service
public class CollectServiceImpl implements CollectService{
    private final CollectDao collectDao;
    private final IConverter iConverter;
    private final ArticleDao articleDao;
    private final JwtUtils jwtUtils;
    private Integer resNumber;

    @Autowired
    public CollectServiceImpl(CollectDao collectDao, IConverter iConverter, ArticleDao articleDao, JwtUtils jwtUtils) {
        this.collectDao = collectDao;
        this.iConverter = iConverter;
        this.articleDao = articleDao;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public GlobalOutPutDTO add(Integer articleId) {
        Collect collect = collectDao.selectOne(new QueryWrapper<Collect>()
                .eq("collect_article_id",articleId)
                .eq("collect_user_id",jwtUtils.getTokenMsg("userId")));
        if (collect == null){
            Collect c = Collect.builder()
                    .collectUserId(Integer.valueOf(jwtUtils.getTokenMsg("userId")))
                    .collectArticleId(articleId)
                    .build();
            collectDao.insert(c);
            return GlobalOutPutDTO.success(1,"文章:"+articleId+"收藏成功");
        }else if (collect != null){
            if (collect.getDeleted() == 0){
                collectDao.deleteById(collect.getKeyId());
                return GlobalOutPutDTO.success(1,"文章:"+articleId+"取消收藏");
            }else {
                collectDao.updateStatus(collect.getKeyId());
                return GlobalOutPutDTO.success(1,"文章:"+articleId+"收藏成功");
            }
        }
        else {
            return GlobalOutPutDTO.fail(0,"文章已被收入二次元异世界");
        }
    }

    @Override
    public GlobalOutPutDTO getList(Integer collectUserId) {
        List<Collect> collectIdList = null;
        if (collectUserId != null){
            collectIdList = collectDao.selectList(new QueryWrapper<Collect>().eq("collect_user_id",collectUserId));
        }else {
            collectIdList = collectDao.selectList(new QueryWrapper<Collect>().eq("collect_user_id",jwtUtils.getTokenMsg("userId")));
        }

        if (collectIdList.size() > 0){
            List<GlobalOutPutDTO> articleList = new ArrayList<>();
            for (int i = 0; i<collectIdList.size(); i++){
                articleList.add(getInfo(collectIdList.get(i).getKeyId()));
            }
            System.out.println(articleList);
            return GlobalOutPutDTO.success(1,"收藏总记录数: "+collectIdList.size()+" 条",articleList);
        }else if(collectIdList.size() == 0){
            return GlobalOutPutDTO.fail(1,"没有收藏任何文章");
        }else {
            throw new SystemException("获取收藏列表失败,请联系管理员");
        }
    }

    @Override
    public GlobalOutPutDTO getInfo(Integer collectId) {
        Collect collect = collectDao.selectOne(new QueryWrapper<Collect>().eq("key_id",collectId).eq("collect_user_id",jwtUtils.getTokenMsg("userId")));
        if (collect != null){
            return GlobalOutPutDTO.success(1,"Article ID: "+ collectId +" info",articleDao.selectById(collect.getCollectArticleId()));
        }else {
            return GlobalOutPutDTO.fail(0,"Article ID"+ collectId +"does nozt exist");
        }
    }

    @Override
    public GlobalOutPutDTO delete(Integer collectId) {
        resNumber = collectDao.deleteById(collectId);
        if (resNumber == 1){
            return GlobalOutPutDTO.success(1,"Successfully delete the favorite with id :"+collectId);
        }else {
            return GlobalOutPutDTO.fail(0,"The collection with id :"+ collectId +" does not exist");
        }
    }

    @Override
    public GlobalOutPutDTO batch(List<Integer> idList) {
        if (idList.size() != 0){
            for (int i = 0; i < idList.size(); i++){
                collectDao.delete(new QueryWrapper<Collect>().eq("key_id",idList.get(i)).eq("collect_user_id",jwtUtils.getTokenMsg("userId")));
            }
            return GlobalOutPutDTO.success(1,"Batch delete successfully",idList);
        }else {
            return GlobalOutPutDTO.fail(0,"There is illegal data, batch deletion fails");
        }
    }
}
