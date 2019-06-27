package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.dao.OrderInfoJpaRepository;
import xiaoqiang.wang.modeldomain.OrderInfo;
import xiaoqiang.wang.modeldomain.UserInfo;
import xiaoqiang.wang.service.IOrderInfoService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderInfoService implements IOrderInfoService {
    @Autowired
    private OrderInfoJpaRepository orderInfoJpaRepository;

    @Override
    public OrderInfo insertOne(UserInfo userInfo, Date orderTimestamp, Short state)
    {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserInfo(userInfo);
        orderInfo.setOrderTimestamp(orderTimestamp);
        orderInfo.setOrderState(state);
        orderInfoJpaRepository.save(orderInfo);
        return orderInfo;
    }

    @Override
    public OrderInfo updateOne(OrderInfo orderInfo)
    {
        orderInfoJpaRepository.save(orderInfo);
        return orderInfo;
    }

    @Override
    public void deleteOne(Long id)
    {
        orderInfoJpaRepository.deleteById(id);
    }

    @Override
    public void deleteMany(List<Long> ids)
    {
        for(Long id : ids) {
            orderInfoJpaRepository.deleteById(id);
        }
    }

    @Override
    public List<OrderInfo> findAll()
    {
        return orderInfoJpaRepository.findAllByOrderByOrderTimestamp();
    }

    @Override
    public OrderInfo findOne(Long id)
    {
        return orderInfoJpaRepository.findById(id).orElse(null);
    }

    @Override
    public OrderInfo findShoppingCart(UserInfo userInfo)
    {
        OrderInfo ret = null;
        List<OrderInfo> orderInfos = orderInfoJpaRepository.findAllByOrderStateAndUserInfo((short)0, userInfo);
        if(orderInfos.isEmpty()) {
            ret = insertOne(userInfo, new Date(), (short)0);
            userInfo.addOrderInfo(ret);
        } else {
            ret = orderInfos.get(0);
        }

        return ret;
    }
}
