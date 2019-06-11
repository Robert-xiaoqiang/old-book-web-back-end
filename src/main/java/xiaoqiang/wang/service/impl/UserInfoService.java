package xiaoqiang.wang.service.impl;

import xiaoqiang.wang.service.IUserInfoService;
import xiaoqiang.wang.dao.UserInfoJpaRepository;
import xiaoqiang.wang.modeldomain.UserInfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserInfoService implements IUserInfoService {

    @Autowired
    private UserInfoJpaRepository userInfoJpaRepository;

    @Override
    public void insertOne(String userName, String password, String email)
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        userInfo.setEmail(email);

        userInfoJpaRepository.save(userInfo);
    }

    @Override
    public void deleteByUserName(String userName)
    {
        userInfoJpaRepository.deleteByUserName(userName);
    }

    @Override
    public UserInfo findByUserName(String userName)
    {
        return userInfoJpaRepository.findByUserName(userName);
    }

    @Override
    public UserInfo findByEmail(String email) {
        return userInfoJpaRepository.findByEmail(email);
    }

    @Override
    public List<UserInfo> findAll()
    {
        return userInfoJpaRepository.findAll();
    }
}
