package cn.fwhspace.iceblog.businessmodule.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/9 16:42
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户信息详情DTO",description = "用户信息详情出参描述")
public class UserPwdDTO {
    private Integer keyId;
    @NotNull(message = "密码不符合规范")
    private String password;
}
