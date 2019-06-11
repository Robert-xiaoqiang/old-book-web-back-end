package xiaoqiang.wang.service;

import java.util.List;
import xiaoqiang.wang.modeldomain.UserInfo;

public interface IUserInfoService {
    public void insertOne(String userName, String password, String email);
    public void deleteByUserName(String userName);
    public UserInfo findByUserName(String userName);
    public UserInfo findByEmail(String email);
    public List<UserInfo> findAll();
}
