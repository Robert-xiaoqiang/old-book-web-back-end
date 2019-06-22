package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.dao.BookBuyJpaRepository;
import xiaoqiang.wang.modeldomain.BookBuy;
import xiaoqiang.wang.service.IBookBuyService;

import java.util.List;

@Service
@Transactional
public class BookBuyService implements IBookBuyService {
    @Autowired
    BookBuyJpaRepository bookBuyJpaRepository;

    @Override
    public BookBuy insertOne(BookBuy bookBuy)
    {
        bookBuyJpaRepository.save(bookBuy);
        return bookBuy;
    }

    @Override
    public List<BookBuy> findAll()
    {
        return bookBuyJpaRepository.findAll();
    }
}
