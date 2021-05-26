package cn.fwhspace.iceblog.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 15:28
 * @description 收藏
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "文章收藏实体类",description = "文章收藏实体类描述")
public class Collect extends BasePojo{

    private Integer collectArticleId;

    private Integer collectUserId;

}
