package cn.fwhspace.iceblog.businessmodule.search.controller;

import cn.fwhspace.iceblog.businessmodule.search.service.SearchService;
import cn.fwhspace.iceblog.common.enums.AccountIdentity;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/26 12:05
 * @description 资源检索
 */

@Api(value = "资源检索", tags = "资源检索业务接口")
@RestController
@RequestMapping(value = "/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/getRound")
    @ApiOperation(value = "随便看看", notes = "对应系统随机推荐(10篇)接口")
    public GlobalOutPutDTO getArticlesListByRound() {
        return searchService.getArticlesListByRound();
    }

    @GetMapping("/getarticlebyname")
    @ApiOperation(value = "博文检索", notes = "对应首页初始化接口/文章检索接口")
    public GlobalOutPutDTO getArticleByName(String articleName, Integer current, Integer pageSize, AccountIdentity accountIdentity) {
        return searchService.getArticleByName(articleName, current, pageSize, accountIdentity);
    }

}
