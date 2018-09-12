package com.yule.demo.web.ctrl;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author yule
 * @date 2018/7/18 22:07
 */
@Component
public class ExtHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // 视图显示专门的错误页
        ModelAndView modelAndView = new ModelAndView("yule/demo/demoScroll");
        return modelAndView;
    }
}
