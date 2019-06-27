package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.OrderInfo;
import xiaoqiang.wang.modeldomain.UserInfo;

import java.util.List;

public interface OrderInfoJpaRepository extends JpaRepository<OrderInfo, Long> {
    public List<OrderInfo> findAllByOrderByOrderTimestamp();
    public List<OrderInfo> findAllByOrderStateAndUserInfo(Short orderState, UserInfo userInfo);
}
