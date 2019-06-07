package xiaoqiang.wang.service;

import xiaoqiang.wang.modeldomain.BookInfo;

import java.util.*;

public interface IBookInfoService {
    public void insertOne(BookInfo bi);
    public List<BookInfo> findAll();
    public void deleteAll();
}
