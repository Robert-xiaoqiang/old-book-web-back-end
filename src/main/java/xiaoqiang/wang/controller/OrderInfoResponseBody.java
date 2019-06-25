package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.OrderInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderInfoResponseBody implements Serializable {
    private final static long serialVersion = 1l;

    private List<OrderDetailResponseBody> orderDetails;
    private String buyerName;
    private Date orderTimestamp;
    private short orderState;
    private double totalPrice;
    private Long key;

    public OrderInfoResponseBody(OrderInfo orderInfo)
    {
        this.orderDetails = orderInfo.getOrderDetails().stream()
                .map(OrderDetailResponseBody::new)
                .collect(Collectors.toList());
        this.totalPrice = orderInfo.getOrderDetails().stream()
                .mapToDouble(od -> od.getBookSell().getSellPrice())
                .sum();
        this.buyerName = orderInfo.getUserInfo().getUserName();
        this.orderTimestamp = orderInfo.getOrderTimestamp();
        this.orderState = orderInfo.getOrderState();
        this.key = orderInfo.getId();
    }

    public short getOrderState() {
        return orderState;
    }

    public void setOrderTimestamp(Date orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public Date getOrderTimestamp() {
        return orderTimestamp;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setOrderDetails(List<OrderDetailResponseBody> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public static long getSerialVersion() {
        return serialVersion;
    }

    public void setOrderState(short orderState) {
        this.orderState = orderState;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetailResponseBody> getOrderDetails() {
        return orderDetails;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }
}
