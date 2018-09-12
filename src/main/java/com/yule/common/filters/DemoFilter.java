package com.yule.common.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器
 * @author yule
 * @date 2018/7/2 21:52
 */
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("demo过滤器init。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("demo过滤器doFilter。。。此处省略业务处理逻辑");

        //通过判断是否继续往下走
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
//        System.out.println("demo过滤器destroy。。。");
    }
}
