package cn.fwhspace.iceblog.config.interceptors;

import cn.fwhspace.iceblog.config.outputdto.GlobalOutPutDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/24 10:23
 * @description 全局异常拦截器
 */
@Slf4j(topic = "exceptionLog")
@Component
@Order(999)
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalOutPutDTO> exceptionHandler(Exception e){
        log.error("堆栈发生异常,异常类:{},异常信息:{}：定位信息:{},",e.getClass(),e.getClass(),e.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalOutPutDTO.builder().code(500).msg(e.getMessage()).data(e.getStackTrace()).build());
    }
    @ResponseBody
    @ExceptionHandler(IOException.class)
    public ResponseEntity<GlobalOutPutDTO> ioExceptionHandler(IOException e){
        log.error("io发生异常,异常类:{},异常信息:{}：定位信息:{},",e.getClass(),e.getClass(),e.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalOutPutDTO.builder().code(500).msg(e.getMessage()).data(e.getStackTrace()).build());
    }
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GlobalOutPutDTO> runtimeExceptionHandler(RuntimeException e){
        log.error("运行时发生异常,异常类:{},异常信息:{}：定位信息:{},",e.getClass(),e.getClass(),e.getStackTrace());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalOutPutDTO.builder().code(500).msg(e.getMessage()).data(e.getStackTrace()).build());
    }
}
