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
import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-16 15:08
 */
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        req.setCharacterEncoding("UTF-8");
        //1.调用请求的参数==封装成为Book对象
        Book book= WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.跳转到图书列表界面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookService.deleteBookById(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page");
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过bookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到request域中
        req.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的图书参数
        String id = req.getParameter("id");
        //2.调用bookService.queryBookById查询图书信息
        Book book = bookService.queryBookById(Integer.parseInt(id));
        //3.保存图书信息到request域中
        req.setAttribute("book",book);
        //4.请求转发到
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       //1.获取请求的参数==封装成为Book对象
        Book book=WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.调用BookService.update(book)方法
        bookService.updateBook(book);
        //3.重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo 和 pageSize
        int pageNo=1;
        int pageSize=Page.PAGE_SIZE;
        try {
            pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
            pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        }catch (Exception e)
        {
            System.out.println("zheli chu wenti le !!");
        }
        //2.调用BookService.page(pageNo,pageSize)
        Page<Book> page=bookService.page(pageNo,pageSize);

        page.setUrl("manager/bookServlet?action=page");
        //3.保存Page对象到request域中
        req.setAttribute("page",page);
        req.setAttribute("items", page.getItems());
        //4.请求转发到pages/manager/book_manager.jsp页面中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
