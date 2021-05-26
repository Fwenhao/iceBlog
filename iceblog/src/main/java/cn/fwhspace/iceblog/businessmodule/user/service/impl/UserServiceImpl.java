package cn.fwhspace.iceblog.businessmodule.user.service.impl;

import cn.fwhspace.iceblog.businessmodule.article.dao.ArticleDao;
import cn.fwhspace.iceblog.businessmodule.user.dao.UserAccountHistoryDao;
import cn.fwhspace.iceblog.businessmodule.user.dao.UserInfoHistoryDao;
import cn.fwhspace.iceblog.businessmodule.user.dto.*;
import cn.fwhspace.iceblog.common.convert.IConverter;
import cn.fwhspace.iceblog.common.enums.AccountStatus;
import cn.fwhspace.iceblog.common.utils.JwtUtils;
import cn.fwhspace.iceblog.common.utils.RedisUtils;
import cn.fwhspace.iceblog.config.exception.SystemException;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.articles.Article;
import cn.fwhspace.iceblog.pojo.users.User;
import cn.fwhspace.iceblog.businessmodule.user.dao.UserDao;
import cn.fwhspace.iceblog.businessmodule.user.service.UserService;
import cn.fwhspace.iceblog.pojo.users.UserAccountHistory;
import cn.fwhspace.iceblog.pojo.users.UserInfoHistory;
import cn.fwhspace.iceblog.tripartite.mail.AliMailUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.fwhspace.iceblog.common.enums.ActivateStatus.ACTIVATED;
import static cn.fwhspace.iceblog.common.enums.ActivateStatus.NOTACTIVATED;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 14:01
 * @description 业务逻辑层
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final IConverter iConverter;
    private final UserAccountHistoryDao accountHistoryDao;
    private final UserInfoHistoryDao infoHistoryDao;
    private final RedisUtils redisUtils;
    private final JwtUtils jwtUtils;
    private final AliMailUtils aliMailUtils;
    private final ArticleDao articleDao;
    /**
     * @param 相应条数判定
     */
    private Integer resNumbers;

    @Autowired
    public UserServiceImpl(UserDao userDao, IConverter iConverter, UserAccountHistoryDao accountHistoryDao, UserInfoHistoryDao infoHistoryDao, RedisUtils redisUtils, JwtUtils jwtUtils, AliMailUtils aliMailUtils, ArticleDao articleDao) {
        this.userDao = userDao;
        this.iConverter = iConverter;
        this.accountHistoryDao = accountHistoryDao;
        this.infoHistoryDao = infoHistoryDao;
        this.redisUtils = redisUtils;
        this.jwtUtils = jwtUtils;
        this.aliMailUtils = aliMailUtils;
        this.articleDao = articleDao;
    }

    @Override
    public GlobalOutPutDTO signInto(UserSignIntoDTO intoDTO) {
        Map<String,String> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",intoDTO.getUserName()).eq("user_password",DigestUtils.md5Hex(intoDTO.getUserPassword()))  ;
        List<User> userList = userDao.selectList(queryWrapper);
        if (userList.size() == 0){
            return GlobalOutPutDTO.fail(0,"用户不存在或账号密码不匹配");
        } else if(userList.size() == 1){
            if(userList.get(0).getUserStatus() == NOTACTIVATED){
                return GlobalOutPutDTO.fail(-1,"用户帐号未激活");
            }else{
                //封装token
                map.put("keyId", String.valueOf(userList.get(0).getKeyId()));
                map.put("userName",userList.get(0).getUserName());
                map.put("nickName",userList.get(0).getUserNickname());
                map.put("userEmail",userList.get(0).getUserEmail());
                map.put("userSex",String.valueOf(userList.get(0).getUserSex()));
                map.put("userPhone",userList.get(0).getUserPhone());
                map.put("userId", String.valueOf(userList.get(0).getKeyId()));
                map.put("userIdentify",userList.get(0).getIdentify());
                map.put("accountIdentity",String.valueOf(userList.get(0).getAccountIdentity()));

                String token = jwtUtils.setToken(map);
                String key = DigestUtils.md5Hex(token);
                //设置主键过期时间为七天
                redisUtils.set(key,token,604800);
                UserDTO userDTO = iConverter.convert(userList.get(0), UserDTO.class);
                userDTO.setToken(key);
                userDTO.setUserInfo(map);
                //登录记录
                accountHistoryDao.insert(UserAccountHistory.builder().userName(userDTO.getUserInfo().get("userName")).userId(userDTO.getKeyId()).remark(AccountStatus.LOGIN).build());

                return GlobalOutPutDTO.success(1,"登录成功",userDTO);
            }
        }
        else {
            throw new SystemException("用户数据冗余,请联系管理员");
        }
    }

    @Override
    public GlobalOutPutDTO signIn(UserSignInDTO signInDTO) {
        System.out.println("注册信息详情:"+signInDTO);
        Long currentTime = System.currentTimeMillis();
        if (userDao.getUserId(signInDTO.getUserName()) == null){
            signInDTO.setUserPassword(DigestUtils.md5Hex(signInDTO.getUserPassword()));
            userDao.insert(iConverter.convert(signInDTO,User.class));
            //设置激活链接五分钟过期
            redisUtils.set(DigestUtils.md5Hex(signInDTO.getUserName()+signInDTO.getUserEmail()),currentTime,300);
            accountHistoryDao.insert(UserAccountHistory.builder().userName(signInDTO.getUserName()).userId(userDao.getUserId(signInDTO.getUserName())).remark(AccountStatus.REGISTER).build());
            aliMailUtils.simple(signInDTO.getUserEmail()
                    ,signInDTO.getUserName()
                    ,"http://fyyice.cn:8089/user/accountActivation?userName="+signInDTO.getUserName()+"&email="+signInDTO.getUserEmail()+"&sysCurrentTime="+currentTime);
            return GlobalOutPutDTO.success(1,"注册成功！请前往邮箱进行账号激活",signInDTO);
        }else {
            return GlobalOutPutDTO.fail(0,"该用户名已注册");
        }
    }

    @Override
    public GlobalOutPutDTO updateInfo(UserUpdateDTO updateDTO) {
        //获取当前用户keyId
        updateDTO.setKeyId(Integer.valueOf(jwtUtils.getTokenMsg("keyId")));
        resNumbers = userDao.updateById(iConverter.convert(updateDTO,User.class));
        if (resNumbers > 0){
            UserInfoHistory userInfoHistory = iConverter.convert(updateDTO, UserInfoHistory.class);
            userInfoHistory.setUserId(updateDTO.getKeyId());
            infoHistoryDao.insert(userInfoHistory);
            accountHistoryDao.insert(UserAccountHistory.builder().userName(userDao.getUserName(updateDTO.getKeyId())).userId(updateDTO.getKeyId()).remark(AccountStatus.UPDATE).build());
            return GlobalOutPutDTO.success(1,"Update successful",updateDTO);
        }else {
            throw new SystemException("用户数据更新失败，请联系管理员");
        }
    }

    @Override
    public GlobalOutPutDTO logOut(String token) {
        redisUtils.del(token);
        return GlobalOutPutDTO.success(1,"退出登录成功",token);
    }

    @Override
    public GlobalOutPutDTO updatePaw(UserPwdDTO pwdDTO) {
        String newPsw = pwdDTO.getPassword();
        if (newPsw != null && newPsw.length() >= 6){
            pwdDTO.setPassword(DigestUtils.md5Hex(newPsw));
            resNumbers = userDao.updateById(iConverter.convert(pwdDTO,User.class));
            if (resNumbers > 0){
                return GlobalOutPutDTO.success(1,"Update password successful",pwdDTO);
            }else {
                throw new SystemException("密码更新失败，请联系管理员");
            }
        }else {
            return GlobalOutPutDTO.fail(0,"The password cannot be null and has a length of at least 6");
        }
    }

    @Override
    public GlobalOutPutDTO accountActivation(String userName, String email,Long sysCurrentTime) {
        Long sendTime = (Long) redisUtils.get(DigestUtils.md5Hex(userName+email));
        if (sendTime == null){
            userDao.deleteRegisterUser(userName,email);
            return GlobalOutPutDTO.fail(0,"链接已过期，我已经把你提交的注册信息给删了，去重新提交吧");
        }
        if ((sendTime - sysCurrentTime) == 0){
            //五分钟有效时间
            if ((System.currentTimeMillis()-sysCurrentTime) < (1000*60*5)){
                System.out.println("进入激活判断");
                User user = userDao.selectOne(new QueryWrapper<User>().eq("user_name",userName).eq("user_email",email).eq("user_status",0));
                if (user != null){
                    user.setUserStatus(ACTIVATED);
                    userDao.update(user,new QueryWrapper<User>().eq("user_name",userName));
                    return GlobalOutPutDTO.success(1,"账号激活成功");
                }else {
                    return GlobalOutPutDTO.fail(0,"用户账号已激活");
                }
            }
        }
        throw new SystemException("小比崽子,直接post我的接口？参数不是你这么输入的");
    }

    @Override
    public GlobalOutPutDTO getLocalInfo() {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("keyId",jwtUtils.getTokenMsg("keyId"));
        userInfo.put("userName",jwtUtils.getTokenMsg("userName"));
        userInfo.put("nickName",jwtUtils.getTokenMsg("nickName"));
        userInfo.put("userEmail",jwtUtils.getTokenMsg("userEmail"));
        userInfo.put("userSex",jwtUtils.getTokenMsg("userSex"));
        userInfo.put("vCount",getLocalVisitorCount());
        userInfo.put("articleCount",getLocalArticleCount());
        userInfo.put("collectCount",getLocalCollectCount());
        userInfo.put("accountIdentity",jwtUtils.getTokenMsg("accountIdentity"));
        if (userInfo != null){
            return GlobalOutPutDTO.success(1,"用户详情",userInfo);
        }else{
            throw new SystemException("获取用户信息错误！");
        }
    }

    @Override
    public Integer getLocalArticleCount() {
        Integer localArticleCount = userDao.getLocalArticleCount(jwtUtils.getTokenMsg("userName"));
        return localArticleCount;
    }

    @Override
    public Integer getLocalVisitorCount() {
        Integer localVisitorCount = 0;
        String userName = jwtUtils.getTokenMsg("userName");
        List<Article> articleIds = articleDao.selectList(new QueryWrapper<Article>().select("v_count").eq("author",userName));
        for (int i = 0; i < articleIds.size(); i++) {
            localVisitorCount += articleIds.get(i).getVCount();
        }
        return localVisitorCount;
    }

    @Override
    public Integer getLocalCollectCount() {
        Integer localArticleCount = userDao.getLocalCollectCount(Integer.valueOf(jwtUtils.getTokenMsg("keyId")));
        return localArticleCount;
    }
}
