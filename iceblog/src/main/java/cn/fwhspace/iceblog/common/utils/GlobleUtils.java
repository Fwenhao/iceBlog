package cn.fwhspace.iceblog.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/8 11:03
 * @description
 */

@Component
public class GlobleUtils {

    /**
     * @description : 〈获取全局token〉
     * @param  1
     * @return : java.lang.String
     */
    public String getAccessToken(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader("token");
    }

}
