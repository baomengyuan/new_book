package com.swjtu.test;

import com.swjtu.dao.OrderDao;
import com.swjtu.dao.impl.OrderDaoImpl;
import com.swjtu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.Assert.*;

/**
 * @author baomengyuan
 * @create 2021-10-27 16:13
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        Date date = new Date();
//      一、获取当前系统时间和日期并格式化输出:
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(date);
        OrderDao orderDao=new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567890",dateTime,new BigDecimal(100),0,1));
    }

    @Test
    public void queryOrder() {
        OrderDao orderDao=new OrderDaoImpl();
        System.out.println(orderDao.queryOrder());
    }
}