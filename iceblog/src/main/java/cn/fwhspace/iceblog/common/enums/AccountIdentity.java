package cn.fwhspace.iceblog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum  AccountIdentity {
    /**
     * @description : 〈表示学生身份〉
     */
    STUDENT(1,"学生"),
    /**
     * @description : 〈表示工作者身份〉
     */
    WORKER(2,"工作者"),
    /**
     * @description : 〈表示教育者身份〉
     */
    TEACHER(3,"教育人士");

    @EnumValue
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
