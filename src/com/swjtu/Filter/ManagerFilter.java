package com.swjtu.Filter;


import com.swjtu.pojo.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @author baomengyuan
 * @create 2021-10-27 22:13
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if(user==null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }else if("woshiabao".equals(user.getUsername())){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
