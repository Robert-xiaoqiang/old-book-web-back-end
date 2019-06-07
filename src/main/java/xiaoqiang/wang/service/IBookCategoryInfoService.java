package xiaoqiang.wang.service;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;

import java.util.*;

public interface IBookCategoryInfoService {
    public void insertOne(BookCategoryInfo bookCategoryInfo);
    public List<BookCategoryInfo> findAll();
    public BookCategoryInfo findByCategoryName(String categoryName);
}
