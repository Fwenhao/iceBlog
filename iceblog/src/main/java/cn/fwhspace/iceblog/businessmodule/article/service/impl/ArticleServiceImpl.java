package cn.fwhspace.iceblog.businessmodule.article.service.impl;

import cn.fwhspace.iceblog.businessmodule.article.dao.*;
import cn.fwhspace.iceblog.businessmodule.article.dto.*;
import cn.fwhspace.iceblog.businessmodule.article.service.ArticleService;
import cn.fwhspace.iceblog.common.convert.IConverter;
import cn.fwhspace.iceblog.common.enums.AccountIdentity;
import cn.fwhspace.iceblog.common.utils.GlobleUtils;
import cn.fwhspace.iceblog.common.utils.JwtUtils;
import cn.fwhspace.iceblog.common.utils.RedisUtils;
import cn.fwhspace.iceblog.config.exception.SystemException;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.articles.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 11:37
 * @description
 */

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;
    private final IConverter iConverter;
    private final DraftsDao draftsDao;
    private final ReprintedDao reprintedDao;
    private final BrowseHistoryDao browseHistoryDao;
    private final UpdateHistoryDao updateHistoryDao;
    private final JwtUtils jwtUtils;


    /**
     * @param 相应条数判定
     */
    private Integer resNumbers;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, IConverter iConverter, DraftsDao draftsDao, ReprintedDao reprintedDao, BrowseHistoryDao browseHistoryDao, UpdateHistoryDao updateHistoryDao, JwtUtils jwtUtils) {
        this.articleDao = articleDao;
        this.iConverter = iConverter;
        this.draftsDao = draftsDao;
        this.reprintedDao = reprintedDao;
        this.browseHistoryDao = browseHistoryDao;
        this.updateHistoryDao = updateHistoryDao;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GlobalOutPutDTO update(ArticleUDTO articleUDTO) {
        Article article = iConverter.convert(articleUDTO, Article.class);
        article.setAuthor(jwtUtils.getTokenMsg("userName"));
        resNumbers = articleDao.updateById(article);
        if (resNumbers > 0) {
            articleUDTO.setArticleId(articleUDTO.getKeyId());
            insertUpdateHistory(iConverter.convert(articleUDTO, ArticleUpdateHistoryDTO.class), "update");
            return GlobalOutPutDTO.success(1, "Update success", articleUDTO);
        } else if (resNumbers <= 0) {
            return GlobalOutPutDTO.fail(0, "This data does not logically exist");
        } else {
            throw new SystemException("更新失败，请联系管理员");
        }
    }

    @Override
    public GlobalOutPutDTO delete(Integer id) {
        resNumbers = articleDao.deleteById(id);
        if (resNumbers > 0) {
            return GlobalOutPutDTO.success(1, "success", id);
        } else if (resNumbers <= 0) {
            return GlobalOutPutDTO.fail(0, "This data does not logically exist");
        } else {
            throw new SystemException("删除失败，请联系管理员");
        }
    }

    @Override
    public GlobalOutPutDTO insert(ArticleDTO articleDTO) {
        System.out.println("articleDTO:" + articleDTO);
        Article article = iConverter.convert(articleDTO, Article.class);
        //赋值tags数组
        article.setTags(articleDTO.getTags());
        article.setAuthor(jwtUtils.getTokenMsg("userName"));
        String accountIdentify = jwtUtils.getTokenMsg("accountIdentity");

        System.out.println("article:" + article);
        //作者身份判定
        if (accountIdentify.equals("STUDENT")) {
            article.setAccountIdentity(AccountIdentity.STUDENT);
        } else if (accountIdentify.equals("WORKER")) {
            article.setAccountIdentity(AccountIdentity.WORKER);
        } else if (accountIdentify.equals("TEACHER")) {
            article.setAccountIdentity(AccountIdentity.TEACHER);
        } else {
            throw new SystemException("无名人士" + accountIdentify);
        }
        resNumbers = articleDao.insert(article);
        if (resNumbers > 0) {
            ArticleUpdateHistoryDTO updateHistoryDTO = iConverter.convert(article, ArticleUpdateHistoryDTO.class);
            updateHistoryDTO.setArticleId(article.getKeyId());
            insertUpdateHistory(updateHistoryDTO, "insert");
            return GlobalOutPutDTO.success(1, "文章编写成功", article);
        } else {
            throw new SystemException("插入失败,请联系管理员");
        }
    }

    @Override
    public GlobalOutPutDTO get(String accessToken, Integer articleId) {
        Article articleModel = articleDao.selectById(articleId);
        if (articleModel != null) {
            //访问量+1
            articleModel.setVCount(articleModel.getVCount() + 1);
            resNumbers = articleDao.updateById(articleModel);
            if (resNumbers == 1) {
                //添加浏览记录
                if (accessToken != null) {
                    //判断登录状态
                    browseRecords(accessToken, articleId);
                }
                return GlobalOutPutDTO.success(1, "articleModel", articleModel);
            } else {
                throw new SystemException("访问统计失败,请联系管理员");
            }
        } else if (articleModel == null) {
            return GlobalOutPutDTO.fail(1, "Article does not exist");
        } else {
            throw new SystemException("文章查询失败，请联系管理员");
        }
    }

    @Override
    public GlobalOutPutDTO insertToDrafts(ArticleDraftsDTO draftsDTO) {
        if (draftsDTO != null) {
            ArticleDrafts articleDrafts = iConverter.convert(draftsDTO, ArticleDrafts.class);
            articleDrafts.setAuthor(jwtUtils.getTokenMsg("username"));
            resNumbers = draftsDao.insert(articleDrafts);
            if (resNumbers != 0) {
                return GlobalOutPutDTO.success(1, "Article temporarily saved to the draft box successfully", draftsDTO);
            } else {
                throw new SystemException("暂存失败");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "Can not be empty");
        }
    }

    @Override
    public GlobalOutPutDTO getDraftsById(Integer draftsId) {
        ArticleDrafts drafts = draftsDao.selectById(draftsId);
        if (drafts != null) {
            return GlobalOutPutDTO.success(1, "Draft info.", drafts);
        } else {
            return GlobalOutPutDTO.fail(0, "Draft articles do not exist");
        }
    }

    @Override
    public GlobalOutPutDTO getDraftsByAuthor() {
        String author = jwtUtils.getTokenMsg("userName");
        List<ArticleDrafts> draftsList = draftsDao.selectList(new QueryWrapper<ArticleDrafts>().eq("author", author));
        if (draftsList.size() > 0) {
            return GlobalOutPutDTO.success(1, "Draft articles of " + author, draftsList);
        } else if (draftsList.size() == 0) {
            return GlobalOutPutDTO.fail(0, "There are no draft articles of " + author);
        } else {
            throw new SystemException("获取暂存失败");
        }
    }

    @Override
    public GlobalOutPutDTO insertdrafts(Integer draftsId) {
        ArticleDrafts articleDrafts = draftsDao.selectById(draftsId);
        if (articleDrafts != null) {
            resNumbers = articleDao.insert(iConverter.convert(articleDrafts, Article.class));
            if (resNumbers > 0) {
                draftsDao.updateStatus(draftsId);
                return GlobalOutPutDTO.success(1, "Release success", articleDrafts);
            } else {
                throw new SystemException("发布失败");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "No information at this draftsId");
        }
    }

    @Override
    public GlobalOutPutDTO insertReprinted(ArticleReprintedDTO articleReprintedDTO) {
        Article article = articleDao.selectById(articleReprintedDTO.getArticleId());
        if (article != null) {
            if (reprintedDao.insert(ArticleReprinted.builder()
                    .articleId(articleReprintedDTO.getArticleId())
                    .userId(articleReprintedDTO.getUserId()).build()) > 0) {
                return GlobalOutPutDTO.success(1, "Transfer success", article);
            } else {
                throw new SystemException("转载失败");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "The articles does not exist");
        }
    }

    @Override
    public GlobalOutPutDTO insertUpdateHistory(ArticleUpdateHistoryDTO updateHistoryDTO, String option) {
        if (!option.equals("insert")) {
            List<Integer> numbers = updateHistoryDao.getNumberOfChanges(updateHistoryDTO.getArticleId());
            updateHistoryDTO.setNumberOfChanges(numbers.size());
        }
        if (updateHistoryDTO != null) {
            resNumbers = updateHistoryDao.insert(iConverter.convert(updateHistoryDTO, ArticleUpdateHistory.class));
            if (resNumbers == 1) {
                return GlobalOutPutDTO.success(1, "Blog update record successful", updateHistoryDTO);
            } else {
                throw new SystemException("博客更新记录失败");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "The blog object does not exist");
        }
    }

    @Override
    public void browseRecords(String accessToken, Integer articleId) {
        browseHistoryDao.insert(ArticleBrowseHistory.builder().articleId(articleId).viewer(jwtUtils.getTokenMsg("userName", accessToken)).build());
    }

    @Override
    public GlobalOutPutDTO getBrowseRecords() {
        Page<ArticleBrowseHistory> page = new Page<>(1, 10, true);
        IPage<ArticleBrowseHistory> browseHistoryList = browseHistoryDao.selectPage(page,
                new QueryWrapper<ArticleBrowseHistory>().eq("viewer", jwtUtils.getTokenMsg("userName"))
        );
        if (browseHistoryList.getRecords().size() != 0) {
            return GlobalOutPutDTO.success(1, "Browsing records (only the latest 50 items are kept)", browseHistoryList);
        } else {
            return GlobalOutPutDTO.fail(0, "No recent browsing history");
        }
    }

    @Override
    public GlobalOutPutDTO getTimeLine() {
        List<ArticleTimeLineDTO> list = new ArrayList<>();
        Integer count2019 = articleDao.selectCount(new QueryWrapper<Article>().between("create_time", "2018-12-31", "2019-12-31"));
        List<Article> articleList2019 = articleDao.selectList(new QueryWrapper<Article>().select("key_id", "title", "description", "create_time").between("create_time", "2018-12-31", "2019-12-31").orderByDesc("create_time"));
        Integer count2020 = articleDao.selectCount(new QueryWrapper<Article>().between("create_time", "2019-12-31", "2020-12-31"));
        List<Article> articleList2020 = articleDao.selectList(new QueryWrapper<Article>().select("key_id", "title", "description", "create_time").between("create_time", "2018-12-31", "2020-12-31").orderByDesc("create_time"));
        Integer count2021 = articleDao.selectCount(new QueryWrapper<Article>().ge("create_time", "2020-12-31"));
        List<Article> articleList2021 = articleDao.selectList(new QueryWrapper<Article>().select("key_id", "title", "description", "create_time").ge("create_time", "2020-12-31").orderByDesc("create_time"));

        list.add(ArticleTimeLineDTO.builder().year(2021).count(count2021).article(articleList2021).build());
        list.add(ArticleTimeLineDTO.builder().year(2020).count(count2020).article(articleList2020).build());
        list.add(ArticleTimeLineDTO.builder().year(2019).count(count2019).article(articleList2019).build());

        return GlobalOutPutDTO.success(1, "时间轴", list);
    }

    @Override
    public GlobalOutPutDTO getNotices() {
        Page<Article> page = new Page<>(1, 10, true);
        IPage<Article> notice = articleDao.selectPage(page, new QueryWrapper<Article>().eq("remark", "系统公告"));
        if (notice.getRecords().size() != 0) {
            return GlobalOutPutDTO.success(1, "系统公告通知", notice);
        } else {
            return GlobalOutPutDTO.fail(0, "暂时没有系统公告通知哦！");
        }
    }

    @Override
    public GlobalOutPutDTO getArticleByAuthor(String author, Integer current, Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        Page<Article> page = new Page<>(current, pageSize, true);
        IPage<Article> articleIPage = articleDao.selectPage(page, new QueryWrapper<Article>().eq("author", author).orderByDesc("create_time"));
        if (articleIPage != null) {
            return GlobalOutPutDTO.success(1, "作者:" + author + "原创文章", articleIPage);
        } else {
            return GlobalOutPutDTO.fail(0, "作者没有创作任何文章哦");
        }
    }


}
