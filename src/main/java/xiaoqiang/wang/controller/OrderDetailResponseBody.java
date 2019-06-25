package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.OrderDetail;

import java.io.Serializable;
import java.util.Date;

public class OrderDetailResponseBody implements Serializable {
    private final static long serialVersion = 1l;

    private BookSellResponseBody bookSellResponseBody;
    private String tradePlace;
    private Date tradeTimestamp;
    private Long key;

    public OrderDetailResponseBody(OrderDetail orderDetail)
    {
        this.bookSellResponseBody = new BookSellResponseBody(orderDetail.getBookSell());
        this.tradePlace = orderDetail.getTradePlace();

        // may be null
        this.tradeTimestamp = orderDetail.getTradeTimestamp();
        this.key = orderDetail.getId();
    }

    public static long getSerialVersion() {
        return serialVersion;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getTradePlace() {
        return tradePlace;
    }

    public Date getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    public BookSellResponseBody getBookSellResponseBody() {
        return bookSellResponseBody;
    }

    public void setTradeTimestamp(Date tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }

    public void setBookSellResponseBody(BookSellResponseBody bookSellResponseBody) {
        this.bookSellResponseBody = bookSellResponseBody;
    }
}
