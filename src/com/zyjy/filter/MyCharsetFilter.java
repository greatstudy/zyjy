package com.zyjy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName MyCharsetFilter
 * @Description
 * @Author 清Great
 * @Date 2020/10/29 11:04
 */
public class MyCharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化：读取配置文件web.xml


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //关键代码，过滤的业务代码
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");

        // 传递给下一过滤器
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        //释放资源

    }
}
