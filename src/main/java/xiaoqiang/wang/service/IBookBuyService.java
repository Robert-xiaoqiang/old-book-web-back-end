package xiaoqiang.wang.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.modeldomain.BookBuy;

import java.util.List;

public interface IBookBuyService {
    public void insertOne(BookBuy bookBuy);
    public List<BookBuy> findAll();
}
