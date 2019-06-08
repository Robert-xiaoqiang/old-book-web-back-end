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
    public void insertOne(UserInfo userInfo)
    {
        userInfoJpaRepository.save(userInfo);
    }

    @Override
    public UserInfo findByUserName(String userName)
    {
        List<UserInfo> lui = userInfoJpaRepository.findByUserName(userName);
        return lui.size() > 0 ? lui.get(0) : null;
    }

    @Override
    public List<UserInfo> findAll()
    {
        return userInfoJpaRepository.findAll();
    }
}
