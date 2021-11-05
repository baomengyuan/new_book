package com.swjtu.web;

import com.google.gson.Gson;
import com.swjtu.pojo.Book;
import com.swjtu.pojo.Cart;
import com.swjtu.pojo.CartItem;
import com.swjtu.service.BookService;
import com.swjtu.service.impl.BookServiceImpl;
import com.swjtu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baomengyuan
 * @create 2021-10-25 13:45
 */
public class CarServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String returnPath=req.getHeader("Referer");
        //获取请求的参数：商品编号。通过商品编号得到图书的信息
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //把图书信息转化成为CartItem商品项
        Book book = bookService.queryBookById(id);

        //调用cart.addItem(cartItem)；添加商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName",cartItem.getName());

        //重定向回商品列表页面
        resp.sendRedirect(returnPath);
    }

    protected void ajaxAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String returnPath=req.getHeader("Referer");
        //获取请求的参数：商品编号。通过商品编号得到图书的信息
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //把图书信息转化成为CartItem商品项
        Book book = bookService.queryBookById(id);

        //调用cart.addItem(cartItem)；添加商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());
        Gson gson=new Gson();
        String resultMapJsonString =gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);

    }


    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 删除 了购物车商品项
            cart.deleteItem(id);
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("cart");
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 商品编号 商品数量
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),1);
        Cart cart=(Cart)req.getSession().getAttribute("cart");

        if(cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
