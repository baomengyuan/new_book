package com.swjtu.web;

import com.swjtu.pojo.Book;
import com.swjtu.pojo.Page;
import com.swjtu.service.BookService;
import com.swjtu.service.impl.BookServiceImpl;
import com.swjtu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author baomengyuan
 * @create 2021-10-19 22:35
 */
public class ClientBookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo 和 pageSize
        int pageNo=1;
        int pageSize= Page.PAGE_SIZE;
        try {
            pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
            pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        }catch (Exception e)
        {
            //System.out.println("这里出错了 !!");
        }
        //2.调用BookService.page(pageNo,pageSize)
        Page<Book> page=bookService.page(pageNo,pageSize);

        page.setUrl("client/clientBookServlet?action=page");
        //3.保存Page对象到request域中
        req.setAttribute("page",page);
        req.setAttribute("items", page.getItems());
        //4.请求转发到pages/manager/book_manager.jsp页面中
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo 和 pageSize,min/max
        int pageNo=1;
        int pageSize= Page.PAGE_SIZE;
        int min=0;
        int max=Integer.MAX_VALUE;
        try {
            pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
            pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
            min=WebUtils.parseInt(req.getParameter("min"),0);
            max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        }catch (Exception e)
        {
            //System.out.println("这里出错了 !!");
        }
        StringBuilder sb=new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if(req.getParameter("min")!=null)
        {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);
        page.setUrl(sb.toString());
        //3.保存Page对象到request域中
        req.setAttribute("page",page);
        req.setAttribute("items", page.getItems());
        //4.请求转发到pages/manager/book_manager.jsp页面中
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }
}
