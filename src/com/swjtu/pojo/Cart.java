package com.swjtu.pojo;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baomengyuan
 * @create 2021-10-24 19:38
 */
public class Cart {
    //private Integer totalCount;
    //private BigDecimal totalPrice;
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer, CartItem>();

    public Integer getTotalCount() {
        Integer totalCount=0;
        for(Map.Entry<Integer,CartItem>entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for(Map.Entry<Integer,CartItem>entry:items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    //添加商品项
    public void addItem(CartItem cartItem)
    {
        CartItem item = items.get(cartItem.getId());
        if(item==null){
            //之前没有添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);//数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }
    }

    //删除商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }
    //清空购物车
    public void clear(){
        items.clear();
    }

    //修改商品数量
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否有此商品，如果有，修改商品数量 ，更新总金额
        CartItem cartItem=items.get(id);
        if(cartItem!=null) {
            cartItem.setCount(count);//修改商品数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));//更新总金额
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
