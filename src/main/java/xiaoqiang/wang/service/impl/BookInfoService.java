package xiaoqiang.wang.service.impl;

import xiaoqiang.wang.service.IBookInfoService;
import xiaoqiang.wang.modeldomain.BookInfo;
import xiaoqiang.wang.dao.BookInfoJpaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookInfoService implements IBookInfoService {
    @Autowired
    private BookInfoJpaRepository bookInfoJpaRepository;

    @Override
    public void insertOne(BookInfo bookInfo)
    {
        bookInfoJpaRepository.save(bookInfo);
    }

    @Override
    public List<BookInfo> findAll()
    {
        return bookInfoJpaRepository.findAll();
    }

    @Override
    public void deleteAll()
    {
        bookInfoJpaRepository.deleteAll();
    }
}
