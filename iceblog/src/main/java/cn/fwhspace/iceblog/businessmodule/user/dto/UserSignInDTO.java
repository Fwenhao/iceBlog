package cn.fwhspace.iceblog.businessmodule.user.dto;

import cn.fwhspace.iceblog.common.enums.AccountIdentity;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/24 11:52
 * @description 用户注册DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户注册DTO",description = "用户注册参数描述")
public class UserSignInDTO implements Serializable {
    private static final long serialVersionUID = -5782321654966859777L;

    @NotBlank(message = "昵称不能为空")
    private String userNickname;
    @NotBlank(message = "帐号不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String userPassword;
    @Email(message = "请输入正确的邮箱格式")
    @NotBlank(message = "邮箱不能为空")
    private String userEmail;
    @NotNull(message = "用户身份不能为空")
    private AccountIdentity accountIdentity;
}
