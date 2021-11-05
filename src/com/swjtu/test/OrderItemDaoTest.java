package com.swjtu.test;

import com.swjtu.dao.OrderItemDao;
import com.swjtu.dao.impl.OrderItemDaoImpl;
import com.swjtu.pojo.OrderItem;
import com.swjtu.utils.WebUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author baomengyuan
 * @create 2021-10-27 16:29
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        }

    @Test
    public void queryOrderItemByOrderId() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        String i = "16353260170603";
        long x= WebUtils.parseLong(i,0);
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId(x);
        System.out.println(orderItems);
    }
}