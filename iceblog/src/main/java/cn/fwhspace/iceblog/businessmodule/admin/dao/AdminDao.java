package cn.fwhspace.iceblog.businessmodule.admin.dao;

import cn.fwhspace.iceblog.pojo.admin.AdminNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/2/8 13:20
 * @description
 */

@Mapper
@Repository
public interface AdminDao extends BaseMapper<AdminNotice> {
}
