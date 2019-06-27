package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.dao.OrderDetailJpaRepository;
import xiaoqiang.wang.modeldomain.BookSell;
import xiaoqiang.wang.modeldomain.OrderDetail;
import xiaoqiang.wang.modeldomain.OrderInfo;
import xiaoqiang.wang.service.IOrderDetailService;

import java.util.Date;

@Service
@Transactional
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private OrderDetailJpaRepository orderDetailJpaRepository;

    @Override
    public OrderDetail insertOne(OrderInfo orderInfo, BookSell bookSell, String tradePlace, Date tradeTimestamp)
    {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderInfo(orderInfo);
        orderDetail.setBookSell(bookSell);
        orderDetail.setTradePlace(tradePlace);
        // may be null
        orderDetail.setTradeTimestamp(tradeTimestamp);
        orderDetailJpaRepository.save(orderDetail);
        return orderDetail;
    }

    @Override
    public OrderDetail findOne(Long id)
    {
        return orderDetailJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOne(Long id)
    {
        orderDetailJpaRepository.deleteById(id);
    }
}
