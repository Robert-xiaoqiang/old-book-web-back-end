package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.OrderInfo;

public interface OrderInfoJpaRepository extends JpaRepository<OrderInfo, Long> {

}
