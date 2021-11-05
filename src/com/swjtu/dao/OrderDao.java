package com.swjtu.dao;

import com.swjtu.pojo.Order;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-26 23:18
 */
public interface OrderDao {
    //保存订单
    public int saveOrder(Order order);
    public List<Order> queryOrder();
    public int changeOrderStatus(long orderId,int status);
    public List<Order> queryOrdersByUserId(int userId);
}
