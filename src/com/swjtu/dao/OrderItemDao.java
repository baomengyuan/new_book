package com.swjtu.dao;

import com.swjtu.pojo.OrderItem;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-26 23:20
 */
public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
    public List<OrderItem> queryOrderItemByOrderId(long orderId);
}
