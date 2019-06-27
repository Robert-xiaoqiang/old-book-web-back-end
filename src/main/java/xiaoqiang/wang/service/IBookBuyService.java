package xiaoqiang.wang.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.modeldomain.BookBuy;
import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.modeldomain.UserInfo;

import java.util.List;

public interface IBookBuyService {
    public BookBuy insertOne(BookInfo bookInfo, UserInfo userInfo, double lowerPrice, double upperPrice);
    public void deleteOne(Long id);
    public BookBuy findOne(Long id);
    public List<BookBuy> findAll();
}
