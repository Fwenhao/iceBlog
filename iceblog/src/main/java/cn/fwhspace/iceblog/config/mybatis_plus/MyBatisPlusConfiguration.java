package cn.fwhspace.iceblog.config.mybatis_plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/12/2 15:49
 * @description MyBatisPlus工具类
 */

@Component
public class MyBatisPlusConfiguration {

    /**
     * @description : 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     * @param  1
     * @return : com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //数据库类型
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        //溢出总页数后是否进行处理
        paginationInnerInterceptor.setOverflow(true);
        //添加到拦截器管理
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
