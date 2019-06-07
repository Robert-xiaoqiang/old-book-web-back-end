package xiaoqiang.wang.service;

import java.util.List;
import xiaoqiang.wang.modeldomain.UserInfo;

public interface IUserService {
    public List<UserInfo> findByUserName(String userName);
    public List<UserInfo> findAll();
}
