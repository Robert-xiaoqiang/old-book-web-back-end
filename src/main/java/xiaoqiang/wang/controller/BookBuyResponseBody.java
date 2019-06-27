package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.BookBuy;
import xiaoqiang.wang.modeldomain.BookInfo;

import java.io.Serializable;

public class BookBuyResponseBody implements Serializable {
    private final static long serialVersion = 1l;

    private BookInfoResponseBody bookInfoResponseBody;
    private String sellerName;
    private double lowerPrice;
    private double upperPrice;

    private Long key;

    public BookBuyResponseBody(BookBuy bookBuy)
    {
        this.bookInfoResponseBody = new BookInfoResponseBody(bookBuy.getBookInfo());
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

    public BookInfoResponseBody getBookInfoResponseBody() {
        return bookInfoResponseBody;
    }

    public void setBookInfoResponseBody(BookInfoResponseBody bookInfoResponseBody) {
        this.bookInfoResponseBody = bookInfoResponseBody;
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
