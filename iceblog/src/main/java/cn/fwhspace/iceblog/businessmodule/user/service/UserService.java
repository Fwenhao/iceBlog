package cn.fwhspace.iceblog.businessmodule.user.service;

import cn.fwhspace.iceblog.businessmodule.user.dto.*;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 11:59
 * @description
 */

public interface UserService {

    /**
     * @description : 〈登录〉
     * @param userSignIntoDto 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    GlobalOutPutDTO signInto(UserSignIntoDTO userSignIntoDto);

    /**
     * @description : 〈注册〉
     * @param userSignInDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    GlobalOutPutDTO signIn(UserSignInDTO userSignInDTO);

    /**
     * @description : 〈更新〉
     * @param updateDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    GlobalOutPutDTO updateInfo(UserUpdateDTO updateDTO);

    /**
     * @description : 〈注销〉
     * @param
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    GlobalOutPutDTO logOut(String token);

    /**
     * @description : 〈修改密码〉
     * @param pwdDTO 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    GlobalOutPutDTO updatePaw(UserPwdDTO pwdDTO);

    /**
     *  账号激活
     * @param userName
     * @param email
     * @return
     */
    GlobalOutPutDTO accountActivation(String userName,String email,Long sysCurrentTime);

    /**
     * @description : 〈获取当前登录用户详情〉
     * @param  1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     */
    GlobalOutPutDTO getLocalInfo();

    /**
     * @description : 〈获取原创文章总数〉
     * @param  1
     * @return : java.lang.Integer
     */
    Integer getLocalArticleCount();

    /**
     * @description : 〈获取总访问量数〉
     * @param  1
     * @return : java.lang.Integer
     */
    Integer getLocalVisitorCount();

    /**
     * @description : 〈获取总收藏数〉
     * @param  1
     * @return : java.lang.Integer
     */
    Integer getLocalCollectCount();

}
