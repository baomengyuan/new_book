package com.swjtu.service;

import com.swjtu.pojo.Cart;
import com.swjtu.pojo.Order;
import com.swjtu.pojo.OrderItem;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-27 16:35
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> showAllOrders();
    public void sendOrder(long orderId);
    public List<OrderItem> showOrderDetails(long orderId);
    public List<Order> showMyOrders(int userId);
    public void receiveOrder(long orderId);
}
