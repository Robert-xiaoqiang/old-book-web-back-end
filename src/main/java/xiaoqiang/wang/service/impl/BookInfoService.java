package xiaoqiang.wang.service.impl;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;
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
    public BookInfo insertOne(String bookName, String bookIntro,
                              String bookIntroURL, String bookImageURL,
                              List<BookCategoryInfo> bookCategoryInfos)
    {
        final BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName(bookName);
        bookInfo.setBookIntro(bookIntro);
        bookInfo.setBookIntroURL(bookIntroURL);
        bookInfo.setBookImageURL(bookImageURL);
        bookCategoryInfos.stream().forEach(c -> {
            bookInfo.addBookCategoryInfo(c);
            return;
        });
        bookInfoJpaRepository.save(bookInfo);
        return bookInfo;
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
