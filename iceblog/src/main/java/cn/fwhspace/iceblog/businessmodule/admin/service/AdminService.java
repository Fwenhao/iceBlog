package cn.fwhspace.iceblog.businessmodule.admin.service;

import cn.fwhspace.iceblog.config.interceptors.GlobalExceptionHandler;
import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import cn.fwhspace.iceblog.pojo.admin.AdminNotice;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/2/8 13:01
 * @description
 */

public interface AdminService {

    /**
     * @param notice 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈创建通知公告〉
     */
    GlobalOutPutDTO createNotice(AdminNotice notice);

    /**
     * @param keyId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈删除通知〉
     */
    GlobalOutPutDTO delNotice(Integer keyId);

    /**
     * @param current 1
     * @return : cn.fwhspace.iceblog.config.interceptors.GlobalOutPutDTO
     * @description : 〈获取通知公告〉
     */
    GlobalOutPutDTO getNotice(Integer current);

    /**
     * @param keyId 1
     * @return : cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO
     * @description : 〈获取公告通知详情〉
     */
    GlobalOutPutDTO getNoticeInfo(Integer keyId);

}
