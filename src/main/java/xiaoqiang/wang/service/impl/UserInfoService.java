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

    public List<UserInfo> findAll()
    {
        return userInfoJpaRepository.findAll();
    }
}
