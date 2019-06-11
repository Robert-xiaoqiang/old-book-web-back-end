package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.OrderDetail;

public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Long> {

}
