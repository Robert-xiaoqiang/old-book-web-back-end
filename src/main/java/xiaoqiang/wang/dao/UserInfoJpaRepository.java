package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.UserInfo;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {

}
