package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.BookInfo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class BookInfoResponseBody implements Serializable {
    private final static long serialVersion = 1l;

    private long key;
    private String bookName;
    private String bookIntro;
    private String bookIntroURL;
    private String bookImageURL;
    private List<BookCategoryInfoResponseBody> bookCategoryInfoResponseBodies;

    public BookInfoResponseBody(BookInfo bookInfo)
    {
        this.key = bookInfo.getId();
        this.bookName = bookInfo.getBookName();
        this.bookIntro = bookInfo.getBookIntro();
        this.bookIntroURL = bookInfo.getBookIntroURL();
        this.bookImageURL = bookInfo.getBookImageURL();

        this.bookCategoryInfoResponseBodies = bookInfo.getBookCategoryInfos().stream()
                .map(BookCategoryInfoResponseBody::new)
                .collect(Collectors.toList());
    }

    public String getBookName() {
        return bookName;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getBookIntro() {
        return bookIntro;
    }

    public void setBookIntroURL(String bookIntroURL) {
        this.bookIntroURL = bookIntroURL;
    }

    public static long getSerialVersion() {
        return serialVersion;
    }

    public String getBookIntroURL() {
        return bookIntroURL;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getKey() {
        return key;
    }

    public void setBookIntro(String bookIntro) {
        this.bookIntro = bookIntro;
    }

    public String getBookImageURL() {
        return bookImageURL;
    }

    public void setBookImageURL(String bookImageURL) {
        this.bookImageURL = bookImageURL;
    }

    public List<BookCategoryInfoResponseBody> getBookCategoryInfoResponseBodies() {
        return bookCategoryInfoResponseBodies;
    }

    public void setBookCategoryInfoResponseBodies(List<BookCategoryInfoResponseBody> bookCategoryInfoResponseBodies) {
        this.bookCategoryInfoResponseBodies = bookCategoryInfoResponseBodies;
    }
}
