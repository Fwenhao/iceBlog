package cn.fwhspace.iceblog.pojo.users;

import cn.fwhspace.iceblog.common.enums.AccountIdentity;
import cn.fwhspace.iceblog.common.enums.ActivateStatus;
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
 * @date 2020/11/23 12:07
 * @description 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户实体类",description = "用户实体类属性详情")
public class User extends BasePojo implements Serializable {
    private static final long serialVersionUID = 1762280256465524274L;
    /**
     * 真实名
     * */
    private String userNickname;

    /**
     * 用户名
     * */
    private String userName;

    /**
     * 密码
     * */
    private String userPassword;

    /**
     * 年龄
     * */
    private Integer userAge;

    /**
     * 性别 默认(1,男)  (0，女)
     * */
    private UserSexStatus userSex;

    /**
     * 手机号
     * */
    private String userPhone;

    /**
     * 邮箱
     * */
    private String userEmail;

    /**
     * 用户权限
     * */
    private String identify;

    /**
     * 用户状态   激活/未激活
     * */
    private ActivateStatus userStatus;

    /**
     * 用户身份
     */
    private AccountIdentity accountIdentity;

}
