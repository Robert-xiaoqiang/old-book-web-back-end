package xiaoqiang.wang.dao;

import xiaoqiang.wang.modeldomain.User;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Repository<User, Long> {
//
//    public List<User> findByUserName(String userName);
//
//    @Query(value = "select username, password from User where username = ?1")
//    public List<User> findByUserName2(String userName);
}
