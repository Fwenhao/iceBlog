package cn.fwhspace.iceblog.businessmodule.collect.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 15:27
 * @description 收藏DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "收藏DTO",description = "收藏DTO基础字段描述")
public class CollectDTO {
    private Integer collectId;

    private Integer collectArticleId;

    @ApiParam(value = "不用输入")
    private Integer collectUserId;
}
