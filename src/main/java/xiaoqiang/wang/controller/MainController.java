package xiaoqiang.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoqiang.wang.service.impl.UserInfoService;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class MainController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/register")
    @ResponseBody
    MyResponseBody register(
            @RequestBody
            Map<String, String> httpRequestBody
    )
    {
        MyResponseBody ret = null;
        String userName = httpRequestBody.get("userName");
        String password = httpRequestBody.get("password");
        String email = httpRequestBody.get("email");

        userInfoService.deleteByUserName(userName);
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

}
