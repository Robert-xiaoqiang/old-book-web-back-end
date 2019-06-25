package xiaoqiang.wang.service;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;
import xiaoqiang.wang.modeldomain.BookInfo;

import java.util.*;

public interface IBookInfoService {
    public BookInfo insertOne(String bookName, String bookIntro,
                              String bookIntroURL, String bookImageURL,
                              List<BookCategoryInfo> bookCategoryInfos);
    public List<BookInfo> findAll();
    public void deleteAll();
}
