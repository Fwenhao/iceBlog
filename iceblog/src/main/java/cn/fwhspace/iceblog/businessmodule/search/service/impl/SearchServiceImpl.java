package cn.fwhspace.iceblog.businessmodule.search.service.impl;

import cn.fwhspace.iceblog.businessmodule.search.dao.SearchDao;
import cn.fwhspace.iceblog.businessmodule.search.service.SearchService;
import cn.fwhspace.iceblog.common.convert.IConverter;
import cn.fwhspace.iceblog.common.enums.AccountIdentity;
import cn.fwhspace.iceblog.common.utils.StringUtils;
import cn.fwhspace.iceblog.config.exception.SystemException;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.articles.Article;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 12:10
 * @description 检索业务逻辑层
 */
@Service(value = "SearchService")
public class SearchServiceImpl implements SearchService {

    private final IConverter iConverter;
    private final SearchDao searchDao;
    private final StringUtils stringUtils;

    @Autowired
    private SearchServiceImpl(IConverter iConverter, SearchDao searchDao, StringUtils stringUtils) {
        this.iConverter = iConverter;
        this.searchDao = searchDao;
        this.stringUtils = stringUtils;
    }

    @Override
    public GlobalOutPutDTO getArticlesListOrderByDesc(String content, String option, Integer pages) {
        return null;
    }

    @Override
    public GlobalOutPutDTO getArticlesListByRound() {
        int current = searchDao.selectCount(null) / 10;
        //随机算法
        //Page<Article> page = new Page<>(new Random().nextInt(current)+1,new Random().nextInt(5)+2);
        Page<Article> page = new Page<>(new Random().nextInt(current) + 1, 5);
        IPage<Article> articleList = searchDao.selectPage(page, new QueryWrapper<Article>().orderByDesc("v_count"));
        if (articleList != null) {
            return GlobalOutPutDTO.success(1, "Round articles", articleList);
        } else {
            throw new SystemException("随机文章生成失败");
        }
    }

    @Override
    public GlobalOutPutDTO getArticleByName(String articleName, Integer current, Integer pageSize, AccountIdentity accountIdentity) {
        //默认初始化页面
        if (articleName == null) {
            Page<Article> page = new Page<>(1, pageSize, true);
            IPage<Article> articleList = searchDao.selectPage(page, new QueryWrapper<Article>().orderByDesc("create_time"));
            if (articleList.getRecords().size() != 0) {
                return GlobalOutPutDTO.success(1, "ArticleLists Record:" + articleList.getRecords().size() + "条", articleList);
            }
        } else {
            if (current == null) {
                current = 1;
            }
            String realArticleName = stringUtils.filter(articleName);
            Page<Article> page = new Page<>(current, pageSize);

            IPage<Article> articleList;
            if (accountIdentity != null) {
                articleList = searchDao.selectPage(page,
                        new QueryWrapper<Article>()
                                .eq("account_identity", accountIdentity.getCode())
                                .and(wrapper -> wrapper.likeRight("key_id", realArticleName)
                                        .or().like("title", realArticleName)
                                        .or().like("description", realArticleName)
                                        .or().like("author", realArticleName)
                                        .or().like("tags", realArticleName))
                                .orderByDesc());
            } else {
                articleList = searchDao.selectPage(page,
                        new QueryWrapper<Article>()
                                .and(wrapper -> wrapper.likeRight("key_id", realArticleName)
                                        .or().like("title", realArticleName)
                                        .or().like("description", realArticleName)
                                        .or().like("author", realArticleName)
                                        .or().like("tags", realArticleName))
                                .orderByDesc());
            }

            //最大页数
            long maxPages = articleList.getPages();
            if (articleList.getRecords().size() != 0 && current <= maxPages) {
                return GlobalOutPutDTO.success(1, "共搜索到:" + articleList.getRecords().size() + "条记录", articleList);
            } else if (articleList.getPages() == 0) {
                return GlobalOutPutDTO.fail(0, "没有与关键词相匹配的结果哦");
            } else {
                return GlobalOutPutDTO.fail(0, "MaxPages has overflow");
            }
        }
        throw new SystemException("分页查询异常，请联系管理员");
    }

}
