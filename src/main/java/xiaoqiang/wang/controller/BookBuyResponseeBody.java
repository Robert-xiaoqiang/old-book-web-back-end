package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.BookBuy;
import xiaoqiang.wang.modeldomain.BookInfo;

import java.io.Serializable;

public class BookBuyResponseeBody implements Serializable {
    private final static long serialVersion = 1l;

    private BookInfo bookInfo;
    private String sellerName;
    private double lowerPrice;
    private double upperPrice;

    private Long key;

    public BookBuyResponseeBody(BookBuy bookBuy)
    {
        this.bookInfo = bookBuy.getBookInfo();
        this.sellerName = bookBuy.getUserInfo().getUserName();
        this.lowerPrice = bookBuy.getLowerPrice();
        this.upperPrice = bookBuy.getUpperPrice();

        this.key = bookBuy.getId();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getUpperPrice() {
        return upperPrice;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public double getLowerPrice() {
        return lowerPrice;
    }

    public void setLowerPrice(double lowerPrice) {
        this.lowerPrice = lowerPrice;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setUpperPrice(double upperPrice) {
        this.upperPrice = upperPrice;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }
}
