package com.yule.common.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by yule on 2018/7/2 22:18.
 */
public class Demo2Filter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("demo2过滤器init  2222222");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("demo过滤器doFilter  222222");

        //通过判断是否继续往下走
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
//        System.out.println("demo2过滤器destroy  22222 ");
    }
}
