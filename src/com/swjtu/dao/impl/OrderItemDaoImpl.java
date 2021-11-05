package com.swjtu.dao.impl;

import com.swjtu.dao.OrderDao;
import com.swjtu.dao.OrderItemDao;
import com.swjtu.pojo.Order;
import com.swjtu.pojo.OrderItem;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-26 23:21
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?)";
        return update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(long orderId) {
        String sql="select `id`,`name`,`count`,`price`,`total_price`totalPrice  from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
