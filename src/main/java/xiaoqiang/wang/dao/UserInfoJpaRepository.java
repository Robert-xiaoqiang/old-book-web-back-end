package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.UserInfo;

import java.util.List;

public interface UserInfoJpaRepository extends JpaRepository<UserInfo, Long> {
    public List<UserInfo> findByUserName(String userName);
}
