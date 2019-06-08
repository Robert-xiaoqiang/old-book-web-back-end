package xiaoqiang.wang.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.modeldomain.BookSell;

import java.util.List;

public interface IBookSellService {
    public void insertOne(BookSell bookSell);
    public void deleteById(long Id);
    public List<BookSell> findAll();
}
