package cn.fwhspace.iceblog.businessmodule.collect.controller;

import cn.fwhspace.iceblog.businessmodule.collect.dto.CollectDTO;
import cn.fwhspace.iceblog.businessmodule.collect.service.CollectService;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 15:26
 * @description 收藏业务接口
 */

@RestController
@RequestMapping("/collect")
@Api(tags = "文章收藏业务接口", description = "文章收藏业务接口描述")
public class CollectController {

    private final CollectService collectService;

    @Autowired
    public CollectController(CollectService collectService) {
        this.collectService = collectService;
    }

    @PostMapping("/add")
    @ApiOperation(value = "文章收藏")
    public GlobalOutPutDTO add(Integer articleId, @RequestHeader("token") String token) {
        return collectService.add(articleId);
    }

    @GetMapping("/getlist")
    @ApiOperation(value = "获取收藏文章列表", notes = "对应收藏人发布作者")
    public GlobalOutPutDTO getList(Integer collectUserId, @RequestHeader("token") String token) {
        return collectService.getList(collectUserId);
    }

    @GetMapping("/getinfo")
    @ApiOperation(value = "获取收藏文章详情(摒弃这个接口，详情获取统一改用文章详情接口)", notes = "对应收藏人发布作者")
    public GlobalOutPutDTO getInfo(Integer collectId, @RequestHeader("token") String token) {
        return collectService.getInfo(collectId);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除收藏", notes = "删除当前用户收藏的文章")
    public GlobalOutPutDTO delete(Integer collectUserId, @RequestHeader("token") String token) {
        return collectService.delete(collectUserId);
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除收藏", notes = "批量删除当前用户收藏的文章")
    public GlobalOutPutDTO batch(@RequestParam(value = "idList") List<Integer> idList, @RequestHeader("token") String token) {
        return collectService.batch(idList);
    }
}
