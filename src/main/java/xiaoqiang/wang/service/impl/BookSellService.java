package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xiaoqiang.wang.dao.BookSellJpaRepository;
import xiaoqiang.wang.modeldomain.BookSell;
import xiaoqiang.wang.service.IBookSellService;

import java.util.List;

@Service
@Transactional
public class BookSellService implements IBookSellService {
    @Autowired
    BookSellJpaRepository bookSellJpaRepository;

    @Override
    public void insertOne(BookSell bookSell)
    {
        bookSellJpaRepository.save(bookSell);
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
}
