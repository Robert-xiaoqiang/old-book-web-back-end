package xiaoqiang.wang.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.modeldomain.BookCategoryInfo;
import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.modeldomain.BookSell;
import xiaoqiang.wang.modeldomain.UserInfo;

import java.util.List;

public interface IBookSellService {
    public BookSell insertOne(BookInfo bookInfo, UserInfo userInfo, double originPrice, double sellPrice);
    public void deleteOne(long Id);
    public List<BookSell> findAll();
    public List<BookSell> findAllByBookCategoryInfos(List<BookCategoryInfo> bookCategoryInfos);
    public BookSell findOne(Long id);
}
