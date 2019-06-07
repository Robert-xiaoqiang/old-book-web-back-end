package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.UserInfo;
import xiaoqiang.wang.service.IUserInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserInfoController {
    @Autowired
    private IUserInfoService iUserInfoService;

    @RequestMapping(value = "/{id}")
    public List<UserInfo> getAll(@PathVariable int id)
    {
        return iUserInfoService.findAll();
    }
}
