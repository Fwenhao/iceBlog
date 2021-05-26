package cn.fwhspace.iceblog.businessmodule.user.controller;

import cn.fwhspace.iceblog.businessmodule.user.dto.*;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.businessmodule.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 11:58
 * @description
 */

@Api(tags = "用户相关操作接口",description = "用户相关操作描述")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signinto")
    @ApiOperation(value = "用户登录接口")
    public GlobalOutPutDTO signInto(@RequestBody(required = false) UserSignIntoDTO userSignIntoDto){
        System.out.println(userSignIntoDto);
        return userService.signInto(userSignIntoDto);
    }

    @PostMapping(value = "/signin")
    @ApiOperation(value = "用户注册接口")
    public GlobalOutPutDTO signIn(@RequestBody @Valid UserSignInDTO userSignInDTO) {
        return userService.signIn(userSignInDTO);
    }

    @ApiOperation(value = "用户更新接口")
    @PutMapping(value = "/updateInfo")
    public GlobalOutPutDTO updateInfo(@RequestBody UserUpdateDTO userUpdateDTO, @RequestHeader("token") String token){
        return userService.updateInfo(userUpdateDTO);
    }

    @ApiOperation(value = "用户退出接口",notes = "用户退出帐号登录,清除当前session")
    @PostMapping(value = "/logout")
    public GlobalOutPutDTO logout(@RequestHeader("token")String token){
        return userService.logOut(token);
    }

    @ApiOperation(value = "用户修改密码",notes = "更改密码")
    @PutMapping(value = "/updatepaw")
    public GlobalOutPutDTO updatePaw(@RequestHeader("token") String token, @RequestBody @Valid UserPwdDTO pwdDTO){
        return userService.updatePaw(pwdDTO);
    }

    @ApiOperation(value = "用户账号激活",notes = "根据用户名和邮箱匹配")
    @GetMapping(value = "/accountActivation")
    public GlobalOutPutDTO accountActivation(String userName,String email,Long sysCurrentTime){
        return userService.accountActivation(userName,email,sysCurrentTime);
    }

    @PostMapping(value = "/getLocalInfo")
    @ApiOperation(value = "获取当前登录用户详情信息")
    public GlobalOutPutDTO signInto(@RequestHeader String token){
        return userService.getLocalInfo();
    }

    @PostMapping(value = "/getLocalArticleCount")
    @ApiOperation(value = "获取当前登录用户原创文章总数")
    public Integer getLocalArticleCount(@RequestHeader String token){
        return userService.getLocalArticleCount();
    }

    @PostMapping(value = "/getLocalVisitorCount")
    @ApiOperation(value = "获取当前登录用户总访问量")
    public Integer getLocalVisitorCount(@RequestHeader String token){
        return userService.getLocalVisitorCount();
    }

    @PostMapping(value = "/getLocalCollectCount")
    @ApiOperation(value = "获取当前登录用户总收集数")
    public Integer getLocalCollectCount(@RequestHeader String token){
        return userService.getLocalCollectCount();
    }
}
