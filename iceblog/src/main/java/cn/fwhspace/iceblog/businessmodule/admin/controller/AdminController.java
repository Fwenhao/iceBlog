package cn.fwhspace.iceblog.businessmodule.admin.controller;

import cn.fwhspace.iceblog.businessmodule.admin.service.AdminService;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.admin.AdminNotice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 10:14
 * @description 管理员
 */
@RestController
@RequestMapping(value = "/admin")
@Api(tags = "管理员操作接口(暂时搁置了，这个模块后面再来完善)", description = "管理员操作接口")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "管理员发布公告", notes = "在首页展示")
    @PostMapping("/create")
    public GlobalOutPutDTO create(@RequestBody AdminNotice notice, @RequestHeader("token") String token) {
        return adminService.createNotice(notice);
    }

    @ApiOperation(value = "管理员删除公告", notes = "删除在首页展示")
    @DeleteMapping("/del/{keyId}")
    public GlobalOutPutDTO create(@PathVariable("keyId") Integer keyId, @RequestHeader("token") String token) {
        return adminService.delNotice(keyId);
    }

    @ApiOperation(value = "获取公告通知", notes = "首页展示")
    @GetMapping("/get")
    public GlobalOutPutDTO getNotice(Integer current) {
        return adminService.getNotice(current);
    }

    @ApiOperation(value = "获取公告通知详情", notes = "info")
    @GetMapping("/getinfo")
    public GlobalOutPutDTO getNoticeInfo(Integer keyId) {
        return adminService.getNoticeInfo(keyId);
    }
}
