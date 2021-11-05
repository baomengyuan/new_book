package com.swjtu.Filter;


import com.swjtu.utils.*;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author baomengyuan
 * @create 2021-10-28 10:51
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        } catch (IOException e) {
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//抛给Tomcat服务器
        }
    }

    @Override
    public void destroy() {
    }
}
