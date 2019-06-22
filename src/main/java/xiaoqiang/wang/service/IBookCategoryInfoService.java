package xiaoqiang.wang.service;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;

import java.util.*;

public interface IBookCategoryInfoService {
    public BookCategoryInfo insertOne(String bookCategoryInfoName, String bookCategoryInfoIntro);
    public List<BookCategoryInfo> findAll();
    public BookCategoryInfo findByCategoryName(String categoryName);
}
