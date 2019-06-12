package xiaoqiang.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoqiang.wang.modeldomain.UserInfo;
import xiaoqiang.wang.service.impl.UserInfoService;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class MainController {
    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "/allusers")
    MyResponseBody register()
    {
        MyResponseBody ret = new MyResponseBody(true, "all data for debugging", userInfoService.findAll());
        return ret;
    }

    @RequestMapping(value = "/register")
    MyResponseBody register(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String email
    )
    {
        MyResponseBody ret = null;
        // userInfoService.deleteByUserName(userName);
        if(userInfoService.findByUserName(userName) != null) {
            ret = new MyResponseBody(false, "userName has already existed", null);
        } else if(userInfoService.findByEmail(email) != null) {
            ret = new MyResponseBody(false, "email has already existed", null);
        } else {
            userInfoService.insertOne(userName, password, email);
            ret = new MyResponseBody(true, null, null);
        }

        return ret;
    }

    @RequestMapping(value = "/login")
    MyResponseBody login(
            @RequestParam String userName,
            @RequestParam String password
    )
    {
        MyResponseBody ret = null;
        UserInfo userInfo = userInfoService.findByUserName(userName);
        if(userInfo == null) {
            ret = new MyResponseBody(false, "userName doesn\'t exist", null);
        } else if(!userInfo.getPassword().equals(password)) {
            ret = new MyResponseBody(false, "password is not matched", null);
        } else {
            ret = new MyResponseBody(true, null, null);
        }
        return ret;
    }

    @RequestMapping(value = "/upload")

}


