package cn.fwhspace.iceblog.pojo.users;

import cn.fwhspace.iceblog.common.enums.UserSexStatus;
import cn.fwhspace.iceblog.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/7 15:26
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户详情更改历史记录",description = "用户详情更改历史记录实体类属性详情")
@Builder
public class UserInfoHistory extends BasePojo implements Serializable {
    private static final long serialVersionUID = -2429228851802500690L;

    private Integer userId;
    private String UserNickname;
    private Integer UserAge;
    private UserSexStatus UserSex;
    private String UserPhone;
    private String UserEmail;
}
