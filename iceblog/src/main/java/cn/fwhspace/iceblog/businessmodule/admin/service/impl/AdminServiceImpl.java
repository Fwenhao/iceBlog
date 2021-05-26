package cn.fwhspace.iceblog.businessmodule.admin.service.impl;

import cn.fwhspace.iceblog.businessmodule.admin.dao.AdminDao;
import cn.fwhspace.iceblog.businessmodule.admin.service.AdminService;
import cn.fwhspace.iceblog.common.utils.JwtUtils;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.admin.AdminNotice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/2/8 13:00
 * @description
 */

@Service(value = "adminServiceImpl")
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final JwtUtils jwtUtils;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, JwtUtils jwtUtils) {
        this.adminDao = adminDao;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GlobalOutPutDTO createNotice(AdminNotice notice) {
        System.out.println(jwtUtils.getTokenMsg("identify"));
        if (jwtUtils.getTokenMsg("userIdentify").equals("admin")) {
            if (notice != null) {
                adminDao.insert(notice);
                return GlobalOutPutDTO.success(1, "Successful Announcement", notice);
            } else {
                return GlobalOutPutDTO.fail(0, "An empty object");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "Insufficient user permissions to operate");
        }

    }

    @Override
    public GlobalOutPutDTO delNotice(Integer keyId) {
        System.out.println(jwtUtils.getTokenMsg("identify"));
        if (jwtUtils.getTokenMsg("userIdentify").equals("admin")) {
            if (adminDao.selectById(keyId) != null) {
                adminDao.deleteById(keyId);
                return GlobalOutPutDTO.success(1, "Announcement deleted successfully");
            } else {
                return GlobalOutPutDTO.fail(0, "Announcement does not exist or has been deleted");
            }
        } else {
            return GlobalOutPutDTO.fail(0, "Insufficient user permissions to operate");
        }
    }

    @Override
    public GlobalOutPutDTO getNotice(Integer current) {
        if (current == null) {
            current = 1;
        }
        Page<AdminNotice> page = new Page<>(current, 5, true);
        IPage<AdminNotice> noticeIPage = adminDao.selectPage(page, new QueryWrapper<AdminNotice>().orderByDesc("create_time"));
        return GlobalOutPutDTO.success(1, "Notices info", noticeIPage);
    }

    @Override
    public GlobalOutPutDTO getNoticeInfo(Integer keyId) {
        if (keyId == null) {
            return GlobalOutPutDTO.fail(0, "keyId is empty");
        }
        AdminNotice notice = adminDao.selectById(keyId);
        if (notice == null) {
            return GlobalOutPutDTO.fail(-1, "Notification does not exist");
        } else {
            return GlobalOutPutDTO.success(1, "Notice Details", notice);
        }
    }
}
