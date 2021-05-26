package cn.fwhspace.iceblog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 16:22
 * @description
 */
@AllArgsConstructor
public enum UserSexStatus {
    MEN(1,"男"),
    WOMEN(0,"女");

    @EnumValue
    private Integer code;
    private String msg;
}
