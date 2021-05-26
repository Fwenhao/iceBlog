package cn.fwhspace.iceblog.config.interceptors;
import cn.fwhspace.iceblog.common.utils.JwtUtils;
import cn.fwhspace.iceblog.common.utils.RedisUtils;
import cn.fwhspace.iceblog.config.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/3 15:54
 * @description
 */

@Slf4j(topic = "authLog")
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token != null && !token.equals("undefined") && token != ""){
            log.debug("当前AccessToken为:{}",token);
            if (redisUtils.get(token) == null){
                throw new SystemException("AccessToken过期");
            }else {
                jwtUtils.verify(token);
            }
        }
        return true;
    }
}
