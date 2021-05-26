package cn.fwhspace.iceblog.businessmodule.qiniu.controller;

import cn.fwhspace.iceblog.businessmodule.qiniu.service.QiniuService;
import cn.fwhspace.iceblog.pojo.file.MyFiles;
import cn.fwhspace.iceblog.tripartite.qiniu.QiniuUtils;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/2/9 13:57
 * @description
 */

@RestController
@RequestMapping(value = "/file")
@Api(tags = "七牛云文件上传接口", description = "文件上传接口")
public class QiniuController {

    private final QiniuService qiniuService;

    @Autowired
    public QiniuController(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    @PostMapping(value = "/uploadByPath")
    @ApiOperation(value = "通过绝对路径上传文件")
    public GlobalOutPutDTO upload(String filePath, String fileName) {
        String address = qiniuService.upload(filePath, fileName);
        return GlobalOutPutDTO.success(1, "上传成功", address);
    }

    @PostMapping(value = "/uploadByMultipartFile")
    @ApiOperation(value = "通过文件上传")
    public GlobalOutPutDTO upload(MultipartFile file) {
        String address = qiniuService.upload(file);
        return GlobalOutPutDTO.success(1, "上传成功", address);
    }

    @PostMapping(value = "/getQiniuFile")
    @ApiOperation(value = "获取七牛云上传的文件信息")
    public GlobalOutPutDTO getQiniuFile() {
        List<MyFiles> qiniuFile = qiniuService.getQiniuFile();
        if (qiniuFile != null) {
            return GlobalOutPutDTO.success(1, "七牛云对象存储仓库", qiniuFile);
        } else {
            return GlobalOutPutDTO.fail(0, "暂时没有任何对象");
        }

    }
}
