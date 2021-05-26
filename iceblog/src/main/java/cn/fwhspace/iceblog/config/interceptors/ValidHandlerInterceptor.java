package cn.fwhspace.iceblog.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fwh
 * @version 1.0
 * @date 2020/11/25 11:42
 * @description 校验异常捕获
 */
@Slf4j(topic = "validLog")
@Component
@Order(1)
@ControllerAdvice
public class ValidHandlerInterceptor {
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    @ResponseBody
    public String bindExceptionHandler(BindException e){
        return e.getBindingResult().getFieldError().getDefaultMessage();
    }
}
