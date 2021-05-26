package cn.fwhspace.iceblog.businessmodule.user.dao;

import cn.fwhspace.iceblog.pojo.users.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 11:59
 * @description 用户接口
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 查询用户id是否存在
     *
     * @param userName*/
    @Select("select key_id from user where user_name = #{userName}")
    Integer getUserId(@NotBlank(message = "帐号不能为空") String userName);

    /**
     * @description : 〈获取用户名〉
     * @param keyId 1
     * @return : java.lang.String
     */
    @Select("select user_name from user where key_id = #{keyId}")
    String getUserName(Integer keyId);

    @Delete("delete from user where user_name = #{username} and user_email = #{email}")
    Integer deleteRegisterUser(@Param("username") String username, @Param("email") String email);

    @Select("select count(*) from article where author = #{username}")
    Integer getLocalArticleCount(@Param("username") String username);

    @Select("select count(*) from collect where collect_user_id = #{userId}")
    Integer getLocalCollectCount(@Param("userId") Integer userId);
}
