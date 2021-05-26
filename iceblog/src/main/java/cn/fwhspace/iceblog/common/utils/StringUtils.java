package cn.fwhspace.iceblog.common.utils;

import org.springframework.stereotype.Component;

/**
 * @author fwh
 * @version 1.0
 * @date 2021/1/6 14:05
 * @description String工具类
 */

@Component
public class StringUtils {

    public String filter(String str){
        //首尾去空格
        str.trim();
        //字符过滤
        String realStr = str.replaceAll("[你我他们的得地嗯哦啊额~!@#$%^&*/()_+<>;'|]","");
        return realStr;
    }

}
