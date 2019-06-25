package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.dao.BookSellJpaRepository;
import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.modeldomain.BookSell;
import xiaoqiang.wang.modeldomain.UserInfo;
import xiaoqiang.wang.service.IBookSellService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookSellService implements IBookSellService {
    @Autowired
    BookSellJpaRepository bookSellJpaRepository;

    @Override
    public BookSell insertOne(BookInfo bookInfo, UserInfo userInfo, double originPrice, double sellPrice)
    {
        BookSell bookSell = new BookSell();
        bookSell.setBookInfo(bookInfo);
        bookSell.setUserInfo(userInfo);
        bookSell.setOriginPrice(originPrice);
        bookSell.setSellPrice(sellPrice);
        bookSellJpaRepository.save(bookSell);
        return bookSell;
    }

    @Override
    public void deleteById(long Id)
    {
        bookSellJpaRepository.deleteById(Id);
    }

    @Override
    public List<BookSell> findAll()
    {
        return bookSellJpaRepository.findAll();
    }

    @Override
    public BookSell findOne(Long id)
    {
        Optional<BookSell> obs = bookSellJpaRepository.findById(id);
        return obs.orElse(null);
    }
}
