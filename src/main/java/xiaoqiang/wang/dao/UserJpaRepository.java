package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.UserInfo;

public interface UserJpaRepository extends JpaRepository<UserInfo, Long> {

}
