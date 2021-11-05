package com.swjtu.test;

import com.swjtu.pojo.Cart;
import com.swjtu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author baomengyuan
 * @create 2021-10-24 20:08
 */
public class CartTest {

    @Test
    public void getTotalCount() {
    }

    @Test
    public void setTotalCount() {
    }

    @Test
    public void getTotalPrice() {
    }

    @Test
    public void setTotalPrice() {
    }

    @Test
    public void getItems() {
    }

    @Test
    public void setItems() {
    }

    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到入土",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.updateCount(1,5);
        System.out.println(cart);
    }
}