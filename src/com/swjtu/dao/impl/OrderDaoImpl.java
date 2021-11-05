package com.swjtu.dao.impl;

import com.swjtu.dao.OrderDao;
import com.swjtu.pojo.Order;

import java.util.List;

/**
 * @author baomengyuan
 * @create 2021-10-26 23:21
 */

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrder() {
        String sql="select `order_id`orderId,`create_time`creatTime,`price`,`status`,`user_id`userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public int changeOrderStatus(long orderId, int status) {
        String sql="update t_order set `status` = ? where `order_id` = ? ";
        return update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(int userId) {
        String sql="select `order_id`orderId,`create_time`creatTime,`price`,`status` from t_order where `user_id`=?";
        return queryForList(Order.class,sql,userId);
    }

}
