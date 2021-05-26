package cn.fwhspace.iceblog.businessmodule.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 17:23
 * @description
 *  UserDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户类DTO",description = "用户类出参描述")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 4880527579444881366L;
    private Integer keyId;
    private String identify;
    private String token;
    private Map<String,String> userInfo;
}
