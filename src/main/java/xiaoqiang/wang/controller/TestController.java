package xiaoqiang.wang.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import xiaoqiang.wang.modeldomain.*;
import xiaoqiang.wang.service.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class TestController {
    @Autowired
    IUserInfoService iUserInfoService;

    @Autowired
    IBookInfoService iBookInfoService;

    @Autowired
    IBookCategoryInfoService iBookCategoryInfoService;

    @Autowired
    IBookBuyService iBookBuyService;

    @Autowired
    IBookSellService iBookSellService;

    @RequestMapping(value = "/")
    public List<BookInfo> test()
    {
//        BookCategoryInfo bci1 = iBookCategoryInfoService.findByCategoryName("AA毛泽东思想");
//        BookCategoryInfo bci2 = iBookCategoryInfoService.findByCategoryName("TP自动化计算机技术");
//
//        BookSell bs = new BookSell();
//        UserInfo ui = iUserInfoService.findByUserName("xiaoqiang");
//        BookInfo bi = new BookInfo();
//
//        bi.setBookName("Hand First Haskell");
//        bi.getBookCategoryInfos().add(bci1);
//        bi.getBookCategoryInfos().add(bci2);
//
//        bci1.getBookInfos().add(bi);
//        bci2.getBookInfos().add(bi);
//
//        bi.setBookIntro("Learning with BUGS");
//        bi.setBookImageURL("12/12.png");
//        bi.getBookSells().add(bs);
//
//        ui.getBookSells().add(bs);
//
//        bs.setBookInfo(bi);
//        bs.setUserInfo(ui);
//        bs.setOriginPrice(12.0);
//        bs.setSellPrice(10.0);
//
//        iBookInfoService.insertOne(bi);
//        iBookSellService.insertOne(bs);
        return iBookInfoService.findAll();
    }
}
