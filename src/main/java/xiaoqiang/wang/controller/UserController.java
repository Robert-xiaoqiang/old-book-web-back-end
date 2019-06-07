package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.UserInfo;
import xiaoqiang.wang.service.IUserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/{id}")
    public List<UserInfo> getUser(@PathVariable int id)
    {
        return iUserService.findAll();
    }
}
