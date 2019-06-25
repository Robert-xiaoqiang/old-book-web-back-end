package xiaoqiang.wang.service;

import xiaoqiang.wang.modeldomain.BookSell;
import xiaoqiang.wang.modeldomain.OrderDetail;
import xiaoqiang.wang.modeldomain.OrderInfo;

import java.util.Date;

public interface IOrderDetailService {
    public OrderDetail insertOne(OrderInfo orderInfo, BookSell bookSell, String tradePlace, Date tradeTimestamp);
    public void deleteOne(Long id);
}
