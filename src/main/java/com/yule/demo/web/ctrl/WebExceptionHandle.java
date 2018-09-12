package com.yule.demo.web.ctrl;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author yule
 * @date 2018/7/18 21:51
 */
@ControllerAdvice
public class WebExceptionHandle {
    @ExceptionHandler(RuntimeException.class)
    public void dealException(){
        System.out.println("hei, throw new Exception123");
    }
}