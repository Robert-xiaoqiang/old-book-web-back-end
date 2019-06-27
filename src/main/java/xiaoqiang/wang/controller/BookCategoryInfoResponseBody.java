package xiaoqiang.wang.controller;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;

import java.io.Serializable;

public class BookCategoryInfoResponseBody implements Serializable {
    private final static long serialVersion = 1l;

    private Long key;
    private String categoryName;
    private String categoryIntro;
    public BookCategoryInfoResponseBody(BookCategoryInfo bookCategoryInfo)
    {
        this.key = bookCategoryInfo.getId();
        this.categoryName = bookCategoryInfo.getCategoryName();
        this.categoryIntro = bookCategoryInfo.getCategoryIntro();
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public static long getSerialVersion() {
        return serialVersion;
    }

    public String getCategoryIntro() {
        return categoryIntro;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryIntro(String categoryIntro) {
        this.categoryIntro = categoryIntro;
    }
}
