package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiaoqiang.wang.dao.BookCategoryInfoJpaRepository;
import xiaoqiang.wang.modeldomain.BookCategoryInfo;
import xiaoqiang.wang.service.IBookCategoryInfoService;

import java.util.List;

@Service
@Transactional
public class BookCategoryInfoService implements IBookCategoryInfoService {
    @Autowired
    private BookCategoryInfoJpaRepository bookCategoryInfoJpaRepository;

    @Override
    public BookCategoryInfo insertOne(String bookCategoryInfoName, String bookCategoryInfoIntro)
    {
        BookCategoryInfo bookCategoryInfo = new BookCategoryInfo();
        bookCategoryInfo.setCategoryName(bookCategoryInfoName);
        bookCategoryInfo.setCategoryIntro(bookCategoryInfoIntro);
        bookCategoryInfoJpaRepository.save(bookCategoryInfo);
        return bookCategoryInfo;
    }

    @Override
    public List<BookCategoryInfo> findAll()
    {
        return bookCategoryInfoJpaRepository.findAll();
    }

    @Override
    public BookCategoryInfo findByCategoryName(String categoryName)
    {
        List<BookCategoryInfo> lbci = bookCategoryInfoJpaRepository.findByCategoryName(categoryName);
        return lbci.size() > 0 ? lbci.get(0) : null;
    }
}
