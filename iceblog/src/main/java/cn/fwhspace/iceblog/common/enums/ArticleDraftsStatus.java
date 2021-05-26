package cn.fwhspace.iceblog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 10:51
 * @description 文章草稿状态
 */

@AllArgsConstructor
@NoArgsConstructor
public enum  ArticleDraftsStatus {
    GetPulish(1,"已发布"),
    NotPulish(0,"未发布");

    @EnumValue
    private Integer code;
    private String status;
}
