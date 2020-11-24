package com.zyjy.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName IsLoginFilter
 * @Description
 * @Author 清Great
 * @Date 2020/10/29 21:02
 */
public class IsLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

//        这边可以获取配置文件中的配置参数

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object adminId = session.getAttribute("adminId");
        StringBuffer requestURL = request.getRequestURL();

//        String servletPath = request.getServletPath();


        //登录超时，或者session过期
        if (adminId == null && !"http://localhost:8080/".contentEquals(requestURL)) {
            System.out.println("重定向了");
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/");
        } else {
            // 传递给下一过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
