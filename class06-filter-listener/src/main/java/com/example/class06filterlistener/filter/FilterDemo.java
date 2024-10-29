package com.example.class06filterlistener.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
@WebFilter("/*")
public class FilterDemo implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       System.out.println("Filter 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FilterDemo 执行");
//        执行请求资源
        chain.doFilter(request, response);

        System.out.println("FilterDemo 资源访问后经过拦截器");
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
