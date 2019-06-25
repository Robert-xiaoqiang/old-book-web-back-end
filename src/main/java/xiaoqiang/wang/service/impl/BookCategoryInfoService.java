package xiaoqiang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xiaoqiang.wang.dao.BookCategoryInfoJpaRepository;
import xiaoqiang.wang.modeldomain.BookCategoryInfo;
import xiaoqiang.wang.service.IBookCategoryInfoService;

import java.util.List;
import java.util.Optional;

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
        Optional<BookCategoryInfo> obci = bookCategoryInfoJpaRepository.findByCategoryName(categoryName);
        return obci.orElse(null);
    }

    @Override
    public BookCategoryInfo findById(Long id)
    {
        Optional<BookCategoryInfo> opb = bookCategoryInfoJpaRepository.findById(id);
        return opb.orElse(null);
    }
}
