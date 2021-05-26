package cn.fwhspace.iceblog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/7 16:06
 * @description
 */
@AllArgsConstructor
public enum  AccountStatus {
    /**
     * @description : 〈表示帐号登录记录〉
     */
    LOGIN(1,"登录"),
    /**
     * @description : 〈表示帐号注册记录〉
     */
    REGISTER(2,"注册"),
    /**
     * @description : 〈表示帐号更新记录〉
     */
    UPDATE(3,"更新");

    @EnumValue
    private Integer code;
    private String msg;
}
