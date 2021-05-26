package cn.fwhspace.iceblog.pojo.admin;

import cn.fwhspace.iceblog.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/2/8 13:02
 * @description 公告实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章收藏实体类",description = "文章收藏实体类描述")
public class AdminNotice extends BasePojo {

    /**
     * 公告标题
     * */
    private String title;
    /**
     * 公告描述
     * */
    private String description;
    /**
     * 公告内容
     * */
    private String content;

    /**
     * 公告类型
     * */
    private String type;

}
