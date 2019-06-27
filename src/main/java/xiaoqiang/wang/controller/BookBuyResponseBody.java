package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.BookBuy;
import xiaoqiang.wang.modeldomain.BookInfo;

import java.io.Serializable;

public class BookBuyResponseBody implements Serializable {
    private final static long serialVersion = 1l;

    private BookInfo bookInfo;
    private String buyerName;
    private double lowerPrice;
    private double upperPrice;

    private Long key;

    public BookBuyResponseBody(BookBuy bookBuy)
    {
        this.bookInfo = bookBuy.getBookInfo();

        this.buyerName = bookBuy.getUserInfo().getUserName();
        this.lowerPrice = bookBuy.getLowerPrice();
        this.upperPrice = bookBuy.getUpperPrice();

        this.key = bookBuy.getId();
    }

    public static long getSerialVersion() {
        return serialVersion;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public double getUpperPrice() {
        return upperPrice;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
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
