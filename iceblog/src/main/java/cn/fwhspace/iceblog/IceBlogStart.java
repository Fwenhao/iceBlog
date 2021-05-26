package cn.fwhspace.iceblog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/23 12:02
 * @description
 */
@SpringBootApplication
@Slf4j(topic = "stdio")
public class IceBlogStart {
    public static void main(String[] args) {
        SpringApplication.run(IceBlogStart.class,args);
        log.info("启动成功");
    }
}
