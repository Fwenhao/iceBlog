package cn.fwhspace.iceblog.tripartite.qiniu;

import cn.fwhspace.iceblog.config.exception.SystemException;
import cn.fwhspace.iceblog.pojo.file.MyFiles;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class QiniuUtils {
    /**
     * 账号的AK和SK
     */
    private static final String accessKey = "your accessKey";
    private static final String secretKey = "your secretKey";
    /**
     * 上传的空间
     */
    private static final String bucketName = "fyyice";

    private static final String address = "http://img.fyyice.cn/";
    /**
     *  密钥凭证 + 环境配置
     */
    private static final Auth auth = Auth.create(accessKey, secretKey);
    private static final Configuration cfg = new Configuration(Region.huanan());
    /**
     * 上传管理
     */
    private static final UploadManager uploadManager = new UploadManager(cfg);
    /**
     * 文件管理
     */
    private static final BucketManager bucketManager = new BucketManager(auth, cfg);

    public String uploadImg(String localFilePath,String name){
        String upToken = auth.uploadToken(bucketName);
        //默认不指定key(name)的情况下，以文件内容的hash值作为文件名
        try {
            //uploadManager.put(localFilePath, key, upToken)  三哥参数分别为文件路径、文件名、生成的校验码
            Response response = uploadManager.put(localFilePath, name, upToken);
            System.out.println(response);
            System.out.println("response:"+response.bodyString());
            if (response.isOK()){
                return address + name;
            }
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    public String uploadImg(MultipartFile file){
        String upToken = auth.uploadToken(bucketName);
        try{
            Response res = uploadManager.put(file.getBytes(),file.getOriginalFilename(),upToken);
            if (res.isOK()){
                return address + file.getOriginalFilename();
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MyFiles> getUploadInfo(){
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";

        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucketName, prefix, limit, delimiter);
        List<MyFiles> myFilesList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        while (fileListIterator.hasNext()){
            FileInfo[] fileInfos = fileListIterator.next();
            for (FileInfo item : fileInfos){
                myFilesList.add(MyFiles.builder().
                        fileName(item.key)
                        .fileType(item.mimeType)
                        .fileSize(item.fsize)
                        .uploadTime(simpleDateFormat.format(new Date(item.putTime)))
                        .url(address+item.key)
                        .build());
            }
        }
        return myFilesList;
    }

}

