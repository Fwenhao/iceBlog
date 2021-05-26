package cn.fwhspace.iceblog.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 13:13
 * @description 接口调用记录
 */
@Component
@Slf4j(topic = "requestLog")
public class RequestLogRecordInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURI();
        String requestIp = request.getRemoteHost();
        String requestUserId = request.getSession().getId();
        log.debug("接口调用:{},请求地址为:{},请求用户id:{}",requestURL,requestIp,requestUserId);
        return true;
    }
}
