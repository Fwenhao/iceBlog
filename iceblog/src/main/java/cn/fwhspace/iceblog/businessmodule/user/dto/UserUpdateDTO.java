package cn.fwhspace.iceblog.businessmodule.user.dto;

import cn.fwhspace.iceblog.common.enums.ActivateStatus;
import cn.fwhspace.iceblog.common.enums.UserSexStatus;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户更新DTO",description = "用户更新出参描述")
public class UserUpdateDTO implements Serializable {
    private static final long serialVersionUID = 2253139616414049698L;

    //指向userId
    private Integer keyId;

    private String UserNickname;

    private Integer UserAge;

    private UserSexStatus UserSex;

    private String UserPhone;

    private String UserEmail;

}
