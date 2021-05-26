package cn.fwhspace.iceblog.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/7 9:08
 * @description 公共字段
 */
@Data
public class BasePojo {
    @TableId(value = "key_id", type = IdType.AUTO)
    private Integer keyId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer deleted;
}
