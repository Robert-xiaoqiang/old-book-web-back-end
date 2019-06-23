package xiaoqiang.wang.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.modeldomain.BookSell;
import xiaoqiang.wang.modeldomain.UserInfo;
import xiaoqiang.wang.service.impl.UserInfoService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class MainController {
    @Autowired
    private UserInfoService userInfoService;

    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/allusers")
    public MyResponseBody allusers()
    {
        MyResponseBody ret = new MyResponseBody(true, "all data for debugging", userInfoService.findAll());
        return ret;
    }

    @RequestMapping(value = "/register")
    public MyResponseBody register(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "avatarBase64") String avatarBase64
    )
    {
        logger.info(avatarBase64);
        MyResponseBody ret = null;
        // userInfoService.deleteByUserName(userName);
        if(userInfoService.findByUserName(userName) != null) {
            ret = new MyResponseBody(false, "userName has already existed", null);
        } else if(userInfoService.findByEmail(email) != null) {
            ret = new MyResponseBody(false, "email has already existed", null);
        } else {
            String avatarURL = uploadImage(avatarBase64);
            if(avatarURL != null) {
                userInfoService.insertOne(userName, password, email, avatarURL);
                ret = new MyResponseBody(true, null, null);
            } else {
                ret = new MyResponseBody(false, "avatar uploading failed", null);
            }
        }

        return ret;
    }

    @RequestMapping(value = "/login")
    public MyResponseBody login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
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

    private String uploadImage(String imageBase64String)
    {
        //byte[] imageByteArray = Base64.getUrlDecoder().decode(imageBase64String);
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imageByteArray = null;
        try {
            imageByteArray = decoder.decodeBuffer(imageBase64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ret = null;
        if(imageByteArray != null) {
            // machine room
            // East-PRC
            Configuration configuration = new Configuration(Zone.zone0());
            UploadManager uploadManager = new UploadManager(configuration);
            String akID = "3Ew5LFXsfvqFuk4YimTh4k3Je0kk4QiOdB32ygQ9";
            String sk = "Z7Cnjg0qyqpMfAgVP4qsEsCzels28f3A6l_W0v6L";
            Auth authentication = Auth.create(akID, sk);
            // bucket name
            String bucket = "old-book-system-bucket";
            String upToken = authentication.uploadToken(bucket);
            // with or without key
            try {
                Response response = uploadManager.put(imageByteArray, null, upToken);
                DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                ret = defaultPutRet.key;
            } catch (QiniuException e) {
                e.printStackTrace();
            }
        }
        return ret == null ? null : "http://psz7tw3xz.bkt.clouddn.com/" + ret;
    }


    @RequestMapping(value = "/upload")
    public MyResponseBody upload(
            @RequestParam String bookName,
            @RequestParam String bookIntro,
            @RequestParam String bookIntroURL,
            @RequestParam String bookImageBase64String,
            @RequestParam List<String> bookCategoryInfos,
            @RequestParam double originPrice,
            @RequestParam double sellPrice
            )
    {
        MyResponseBody ret = null;
        String bookImageURL = uploadImage(bookImageBase64String);
        BookInfo bookInfo = new BookInfo();
        BookSell bookSell = new BookSell();
//        bookInfo.setBookName(bookName);
//        bookInfo.setBookImageURL();
//        bookInfo.setBookIntro();
//        bookInfo.setBookIntroURL();
//
//        bookSell.set

        return ret;
    }

}


