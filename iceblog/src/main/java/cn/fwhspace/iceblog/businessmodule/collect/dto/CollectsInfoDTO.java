package cn.fwhspace.iceblog.businessmodule.collect.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/1 11:03
 * @description 收藏详情DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "收藏详情DTO",description = "收藏信息详情描述")
public class CollectsInfoDTO {
    private Integer collectUserId;
    private Integer articleId;
    private String articleAuthor;
    private String articleContent;
    private Date articleCreateTime;
    private String articleDesc;
    private String articleTitle;
    private String articleType;
}
