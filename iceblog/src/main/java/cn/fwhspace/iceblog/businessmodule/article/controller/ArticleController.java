package cn.fwhspace.iceblog.businessmodule.article.controller;

import cn.fwhspace.iceblog.businessmodule.article.dto.ArticleDTO;
import cn.fwhspace.iceblog.businessmodule.article.dto.ArticleDraftsDTO;
import cn.fwhspace.iceblog.businessmodule.article.dto.ArticleReprintedDTO;
import cn.fwhspace.iceblog.businessmodule.article.dto.ArticleUDTO;
import cn.fwhspace.iceblog.businessmodule.article.service.ArticleService;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 11:35
 * @description 文章业类描述
 */

@Api(tags = "文章业务操作接口", description = "文章业务操作")
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(@RequestBody ArticleService articleService) {
        this.articleService = articleService;
    }

    @ApiOperation(value = "获取通知公告", notes = "获取管理员发布的系统公告通知")
    @GetMapping("/notices")
    public GlobalOutPutDTO getNotices() {
        return articleService.getNotices();
    }

    @ApiOperation(value = "创建文章接口", notes = "create")
    @PostMapping("/insert")
    public GlobalOutPutDTO insertByArticle(@RequestBody @Valid ArticleDTO articleDTO, @RequestHeader("token") String token) {
        return articleService.insert(articleDTO);
    }

    @ApiOperation(value = "删除文章接口", notes = "delete")
    @DeleteMapping("/delete")
    public GlobalOutPutDTO deleteByArticle(@RequestHeader("token") String token, Integer id) {
        return articleService.delete(id);
    }

    @ApiOperation(value = "更新文章接口", notes = "update")
    @PutMapping("/update")
    public GlobalOutPutDTO updateByArticle(@RequestBody ArticleUDTO articleUDTO, @RequestHeader("token") String token) {
        return articleService.update(articleUDTO);
    }

    @ApiOperation(value = "获取文章详情接口", notes = "get")
    @GetMapping("/get")
    public GlobalOutPutDTO getArticleById(String accessToken, Integer articleId) {
        return articleService.get(accessToken, articleId);
    }

    @ApiOperation(value = "获取文章列表", notes = "get")
    @GetMapping("/get/{author}")
    public GlobalOutPutDTO getArticleByAuthor(@PathVariable("author") String author, Integer current, Integer pageSize) {
        return articleService.getArticleByAuthor(author, current, pageSize);
    }

    @ApiOperation(value = "文章暂存接口", notes = "草稿箱")
    @PostMapping("/drafts/insert")
    public GlobalOutPutDTO insertToDrafts(@RequestBody ArticleDraftsDTO draftsDTO, @RequestHeader("token") String token) {
        return articleService.insertToDrafts(draftsDTO);
    }

    @ApiOperation(value = "通过id获取暂存文章接口", notes = "关键字：id")
    @GetMapping("/drafts/get")
    public GlobalOutPutDTO getDraftsById(Integer draftsId, @RequestHeader("token") String token) {
        return articleService.getDraftsById(draftsId);
    }

    @ApiOperation(value = "通过author获取暂存文章接口", notes = "关键字：author")
    @GetMapping("/drafts/getauthor")
    public GlobalOutPutDTO getDraftsByAuthor(@RequestHeader("token") String token) {
        return articleService.getDraftsByAuthor();
    }

    @ApiOperation(value = "草稿箱文章发布接口", notes = "关键字：draftsId")
    @PostMapping("/drafts/publish")
    public GlobalOutPutDTO insertdrafts(Integer draftsId, @RequestHeader("token") String token) {
        return articleService.insertdrafts(draftsId);
    }

    @ApiOperation(value = "转载接口", notes = "文章搬运")
    @PostMapping("/reprinted/insert")
    public GlobalOutPutDTO insertReprinted(@RequestBody ArticleReprintedDTO articleReprintedDTO, @RequestHeader("token") String token) {
        return articleService.insertReprinted(articleReprintedDTO);
    }

    @ApiOperation(value = "获取用户浏览历史记录接口", notes = "获取文章浏览历史记录")
    @GetMapping("/browserecords/get")
    public GlobalOutPutDTO getBrowseRecords(@RequestHeader("token") String token) {
        return articleService.getBrowseRecords();
    }

    @ApiOperation(value = "获取时间轴", notes = "所有文章的发布时间顺序")
    @GetMapping("/getTimeLine")
    public GlobalOutPutDTO getTimeLine() {
        return articleService.getTimeLine();
    }
}
