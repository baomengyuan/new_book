package com.swjtu.pojo;
import java.math.BigDecimal;


/**
 * @author baomengyuan
 * @create 2021-10-26 23:14
 */
public class Order {
    private String orderId;
    private String creatTime;
    private BigDecimal price;
    private Integer status=0;//0未发货。1表示已经发货 2表示已经签收
    private int userId;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", creatTime=" + creatTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public Order(String orderId, String creatTime, BigDecimal price, Integer status, int userId) {
        this.orderId = orderId;
        this.creatTime = creatTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
