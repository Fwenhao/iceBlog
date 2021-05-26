package cn.fwhspace.iceblog.pojo.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyFiles {
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String uploadTime;
    private String url;
}
