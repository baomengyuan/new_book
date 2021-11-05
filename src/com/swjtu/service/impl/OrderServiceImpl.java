package com.swjtu.service.impl;

import com.swjtu.dao.BookDao;
import com.swjtu.dao.OrderDao;
import com.swjtu.dao.OrderItemDao;
import com.swjtu.dao.impl.BookDaoImpl;
import com.swjtu.dao.impl.OrderDaoImpl;
import com.swjtu.dao.impl.OrderItemDaoImpl;
import com.swjtu.pojo.*;
import com.swjtu.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author baomengyuan
 * @create 2021-10-27 16:36
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        Date date = new Date();
//      一、获取当前系统时间和日期并格式化输出:
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(date);
        String OrderId=System.currentTimeMillis()+""+userId;
        Order order=new Order(OrderId,dateTime,cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);//保存订单
        //遍历购物车中每一个商品项转化成为订单项保存到数据库
        for(Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            CartItem cartItem=entry.getValue();
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),OrderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(cartItem.getId());
            //更新库存和销量
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return OrderId;
    }

    //查询所有的订单
    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrder();
    }

    //发货
    @Override
    public void sendOrder(long orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    //查看单个订单详情
    @Override
    public List<OrderItem> showOrderDetails(long orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(int userId) {
        return orderDao.queryOrdersByUserId(userId);
    }
    //签收订单
    @Override
    public void receiveOrder(long orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
