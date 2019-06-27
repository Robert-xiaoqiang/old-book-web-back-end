package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.dao.BookBuyJpaRepository;
import xiaoqiang.wang.modeldomain.BookBuy;
import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.modeldomain.UserInfo;
import xiaoqiang.wang.service.IBookBuyService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookBuyService implements IBookBuyService {
    @Autowired
    BookBuyJpaRepository bookBuyJpaRepository;


    @Override
    public BookBuy insertOne(BookInfo bookInfo, UserInfo userInfo, double lowerPrice, double upperPrice)
    {
        BookBuy bookBuy = new BookBuy();
        bookBuy.setBookInfo(bookInfo);
        bookBuy.setUserInfo(userInfo);
        bookBuy.setLowerPrice(lowerPrice);
        bookBuy.setUpperPrice(upperPrice);

        bookBuyJpaRepository.save(bookBuy);

        return bookBuy;
    }

    @Override
    public void deleteOne(Long id)
    {
        bookBuyJpaRepository.deleteById(id);
    }

    @Override
    public BookBuy findOne(Long id)
    {
        return bookBuyJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookBuy> findAll()
    {
        return bookBuyJpaRepository.findAll();
    }
}
