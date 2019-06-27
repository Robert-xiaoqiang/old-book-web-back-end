package xiaoqiang.wang.service;

import xiaoqiang.wang.dao.OrderInfoJpaRepository;
import xiaoqiang.wang.modeldomain.OrderInfo;
import xiaoqiang.wang.modeldomain.UserInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface IOrderInfoService {
    public OrderInfo insertOne(UserInfo userInfo, Date orderTimestamp, Short state);
    public void deleteOne(Long id);
    public List<OrderInfo> findAll();
    public OrderInfo findOne(Long id);
    public OrderInfo findShoppingCart(UserInfo userInfo);
}
