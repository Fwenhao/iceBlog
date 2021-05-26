package cn.fwhspace.iceblog.businessmodule.qiniu.service;

import cn.fwhspace.iceblog.pojo.file.MyFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QiniuService {
    //通过路径上传
    String upload(String filePath, String fileName);
    //通过文件上传
    String upload(MultipartFile file);

    List<MyFiles> getQiniuFile();

}
