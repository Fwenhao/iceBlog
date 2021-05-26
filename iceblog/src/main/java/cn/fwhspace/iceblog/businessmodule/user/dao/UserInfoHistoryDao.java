package cn.fwhspace.iceblog.businessmodule.user.dao;

import cn.fwhspace.iceblog.pojo.users.UserInfoHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/7 15:55
 * @description
 */

@Mapper
@Repository
public interface UserInfoHistoryDao extends BaseMapper<UserInfoHistory> {

}
