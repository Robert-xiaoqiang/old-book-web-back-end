package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.UnreadMessage;

public interface UnreadMessageJpaRepository extends JpaRepository<UnreadMessage, Long> {

}
