package xiaoqiang.wang.service;

import xiaoqiang.wang.modeldomain.OrderInfo;
import xiaoqiang.wang.modeldomain.UserInfo;

import java.util.Date;
import java.util.List;

public interface IOrderInfoService {
    public OrderInfo insertOne(UserInfo userInfo, Date orderTimestamp, Short state);
    public OrderInfo updateOne(OrderInfo orderInfo);
    public void deleteOne(Long id);
    public void deleteMany(List<Long> ids);
    public List<OrderInfo> findAll();
    public OrderInfo findOne(Long id);
    public OrderInfo findShoppingCart(UserInfo userInfo);
}
