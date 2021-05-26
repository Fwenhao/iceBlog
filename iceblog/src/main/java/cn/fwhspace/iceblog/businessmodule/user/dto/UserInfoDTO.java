package cn.fwhspace.iceblog.businessmodule.user.dto;

import cn.fwhspace.iceblog.common.enums.ActivateStatus;
import cn.fwhspace.iceblog.common.enums.UserSexStatus;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/30 10:46
 * @description 用户信息详情
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户信息详情DTO",description = "用户信息详情出参描述")
public class UserInfoDTO {

    private Integer keyId;

    private Integer userId;

    private String userPassword;

    private String UserNickname;

    private Integer UserAge;

    private UserSexStatus UserSex;

    private String UserPhone;

    private String UserEmail;

    private ActivateStatus userStatus;
}
