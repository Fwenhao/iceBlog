package cn.fwhspace.iceblog.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/8 13:28
 * @description 文章状态
 */
@NoArgsConstructor
@AllArgsConstructor
public enum  ArticleStatus {
    Original(1,"原创"),
    Reprinted(0,"转载");

    @EnumValue
    private Integer code;
    private String msg;
}
