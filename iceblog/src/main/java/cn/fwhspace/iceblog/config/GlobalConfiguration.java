package cn.fwhspace.iceblog.config;

import cn.fwhspace.iceblog.config.interceptors.CrossOriginHandlerInterceptor;
import cn.fwhspace.iceblog.config.interceptors.JwtInterceptor;
import cn.fwhspace.iceblog.config.interceptors.RequestLogRecordInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 13:16
 * @description  拦截器注册
 *  bio  默认隔离级别   aio
 */
@Configuration
@EnableWebMvc
public class GlobalConfiguration implements WebMvcConfigurer {

    private final CrossOriginHandlerInterceptor crossOriginHandlerInterceptor;
    private final RequestLogRecordInterceptor requestLogRecordInterceptor;

    @Autowired
    public GlobalConfiguration(CrossOriginHandlerInterceptor crossOriginHandlerInterceptor, RequestLogRecordInterceptor requestLogRecordInterceptor) {
        this.crossOriginHandlerInterceptor = crossOriginHandlerInterceptor;
        this.requestLogRecordInterceptor = requestLogRecordInterceptor;
    }

    //将拦截器bean化自动注入
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //跨域
        registry.addInterceptor(crossOriginHandlerInterceptor).addPathPatterns("/**");
        //注册拦截器   addPathPatterns("/**")表示所有请求都被拦截
        registry.addInterceptor(requestLogRecordInterceptor).addPathPatterns("/**");
        //jwt token拦截
        registry.addInterceptor(jwtInterceptor()).addPathPatterns("/**");

    }

    //swagger资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-resources/configuration/ui").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //输出格式处理，否则会出现???
//        converters.add(new FastJsonHttpMessageConverter());
//    }
}
