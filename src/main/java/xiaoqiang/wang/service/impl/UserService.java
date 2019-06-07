package xiaoqiang.wang.service.impl;

import xiaoqiang.wang.service.IUserService;
import xiaoqiang.wang.dao.UserJpaRepository;
import xiaoqiang.wang.dao.UserRepository;
import xiaoqiang.wang.modeldomain.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

//    @Autowired
//    private UserRepository userRepository;

    public List<User> findByUserName(String userName)
    {
        return new ArrayList<>();
    }

    public List<User> findAll()
    {
        return userJpaRepository.findAll();
    }
}
