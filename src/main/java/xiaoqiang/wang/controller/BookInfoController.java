package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;
import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.service.IBookCategoryInfoService;
import xiaoqiang.wang.service.IBookInfoService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookInfoController {
    @Autowired
    private IBookInfoService iBookInfoService;
    @Autowired
    private IBookCategoryInfoService iBookCategoryInfoService;

    @RequestMapping(value = "/")
    public List<BookInfo> test()
    {
        BookCategoryInfo bci1 = iBookCategoryInfoService.findByCategoryName("AA毛泽东思想");
        BookCategoryInfo bci2 = iBookCategoryInfoService.findByCategoryName("TP自动化计算机技术");

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName("Java Programming Language");
        bookInfo.setBookIntro("NBNB666");

        bci1.getBookInfos().add(bookInfo);
        bci2.getBookInfos().add(bookInfo);

        bookInfo.getBookCategoryInfos().add(bci1);
        bookInfo.getBookCategoryInfos().add(bci2);

        iBookInfoService.deleteAll();
        iBookInfoService.insertOne(bookInfo);
        return new ArrayList<>();
    }
}
