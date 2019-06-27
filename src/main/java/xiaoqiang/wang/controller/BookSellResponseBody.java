package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.modeldomain.BookSell;

import java.io.Serializable;

public class BookSellResponseBody implements Serializable {
    private final static long serialVersion = 1l;

    private Long key;
    private BookInfo bookInfo;
    private String sellerName;
    private double originPrice;
    private double sellPrice;

    private boolean ordered;

    public BookSellResponseBody(BookSell bookSell)
    {
        this.key = bookSell.getId();

        this.bookInfo = bookSell.getBookInfo();
        this.sellerName = bookSell.getUserInfo().getUserName();
        this.originPrice = bookSell.getOriginPrice();
        this.sellPrice = bookSell.getSellPrice();

        this.ordered = bookSell.getOrderDetail() != null;
    }

    public String getSellerName() {
        return sellerName;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public static long getSerialVersion() {
        return serialVersion;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
}
