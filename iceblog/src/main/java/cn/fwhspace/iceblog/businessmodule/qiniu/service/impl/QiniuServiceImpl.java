package cn.fwhspace.iceblog.businessmodule.qiniu.service.impl;

import cn.fwhspace.iceblog.businessmodule.qiniu.service.QiniuService;
import cn.fwhspace.iceblog.pojo.file.MyFiles;
import cn.fwhspace.iceblog.tripartite.qiniu.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class QiniuServiceImpl implements QiniuService {

    @Autowired
    private QiniuUtils qiniuUtils;

    @Override
    public String upload(String filePath, String fileName) {
        return qiniuUtils.uploadImg(filePath,fileName);
    }

    @Override
    public String upload(MultipartFile file) {
        return qiniuUtils.uploadImg(file);
    }

    @Override
    public List<MyFiles> getQiniuFile() {
        return qiniuUtils.getUploadInfo();
    }
}
