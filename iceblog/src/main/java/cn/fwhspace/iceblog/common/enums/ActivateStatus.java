package cn.fwhspace.iceblog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.*;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 15:10
 * @description
 *  用户帐号状态
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum  ActivateStatus {
    ACTIVATED("已激活",1),
    NOTACTIVATED("未激活",0);

    @EnumValue
    private String status;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
