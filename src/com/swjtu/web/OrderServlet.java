package com.swjtu.web;

import com.swjtu.dao.impl.BaseDao;
import com.swjtu.pojo.Cart;
import com.swjtu.pojo.Order;
import com.swjtu.pojo.OrderItem;
import com.swjtu.pojo.User;
import com.swjtu.service.OrderService;
import com.swjtu.service.impl.OrderServiceImpl;
import com.swjtu.utils.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-27 17:01
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //结账需要调用orderService的createOrder方法
        //需要 cart 和 userId
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        User user = (User)req.getSession().getAttribute("user");
        Integer userId=user.getId();
        String orderId= null;
        orderId = orderService.createOrder(cart,userId);
        req.getSession().setAttribute("OrderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
    //展示所有订单
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders",orders);
        //3.请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }
    //发货
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = WebUtils.parseLong(req.getParameter("orderId"),0);
        orderService.sendOrder(orderId);
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showOrderDetail&orderId="+orderId+"&status=1");
    }
    //查看订单详情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId=WebUtils.parseLong(req.getParameter("orderId"),0l);
        int status=WebUtils.parseInt(req.getParameter("status"),0);
        List<OrderItem> orderItems=orderService.showOrderDetails(orderId);
        req.setAttribute("orderId",orderId);//传递订单号，以便后面修改订单状态
        req.setAttribute("orderItems",orderItems);//为了在详情页中展现具体的商品
        req.setAttribute("status",status);//传递订单状态，以便进行字样修改
        req.getRequestDispatcher("/pages/manager/order_details.jsp").forward(req,resp);
    }
    //查看个人用户订单
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = ((User)req.getSession().getAttribute("user")).getId();
        List<Order> orders = orderService.showMyOrders(userId);
        req.setAttribute("MyOrders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }
    //个人用户签收订单
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId=WebUtils.parseLong(req.getParameter("orderId"),0l);
        orderService.receiveOrder(orderId);

        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showMyOrderDetail&orderId="+orderId+"&status=2");
    }
    //查看个人订单情况
    protected void showMyOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId=WebUtils.parseLong(req.getParameter("orderId"),0l);
        int status=WebUtils.parseInt(req.getParameter("status"),0);
        List<OrderItem> orderItems=orderService.showOrderDetails(orderId);
        req.setAttribute("MyOrderId",orderId);//传递订单号，以便后面修改订单状态
        req.setAttribute("MyOrderItems",orderItems);//为了在详情页中展现具体的商品
        req.setAttribute("MyStatus",status);//传递订单状态，以便进行字样修改
        req.getRequestDispatcher("/pages/order/MyOrder_details.jsp").forward(req,resp);
    }
}
