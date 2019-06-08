package xiaoqiang.wang.service;

import java.util.List;
import xiaoqiang.wang.modeldomain.UserInfo;

public interface IUserInfoService {
    public void insertOne(UserInfo userInfo);
    public UserInfo findByUserName(String UserName);
    public List<UserInfo> findAll();
}
