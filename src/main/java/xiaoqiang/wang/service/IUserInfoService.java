package xiaoqiang.wang.service;

import java.util.List;
import xiaoqiang.wang.modeldomain.UserInfo;

public interface IUserInfoService {
    public UserInfo insertOne(String userName, String password, String email, String avatarURL);
    public void deleteByUserName(String userName);
    public UserInfo findByUserName(String userName);
    public UserInfo findByEmail(String email);
    public List<UserInfo> findAll();
}
