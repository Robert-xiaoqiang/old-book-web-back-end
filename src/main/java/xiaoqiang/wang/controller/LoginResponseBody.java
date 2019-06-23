package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.UserInfo;

import java.io.Serializable;

public class LoginResponseBody implements Serializable {
    private String userName;
    private String password;
    private String avatarURL;
    public LoginResponseBody(UserInfo userInfo)
    {
        this.userName = userInfo.getUserName();
        this.password = userInfo.getPassword();
        this.avatarURL = userInfo.getAvatarURL();
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
