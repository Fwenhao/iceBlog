package cn.fwhspace.iceblog.businessmodule.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/24 13:44
 * @description 用户类登录InDTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户类登录InDTO",description = "用户登录入参描述")
public class UserSignIntoDTO implements Serializable {
    private static final long serialVersionUID = 2446489026650656439L;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "用户密码不能为空")
    private String userPassword;
}
