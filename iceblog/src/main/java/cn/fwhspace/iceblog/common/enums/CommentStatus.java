package cn.fwhspace.iceblog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/11 11:47
 * @description
 */
@AllArgsConstructor
public enum  CommentStatus {
    Viewed(1,"已查看"),
    NotViewed(0,"未查看");

    @EnumValue
    private Integer code;
    private String msg;
}
