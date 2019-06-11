package xiaoqiang.wang.modeldomain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "book_category_info")
public class BookCategoryInfo implements Serializable {
    private final static long serialVersionID = 1l;
    @Column(name = "book_category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_category_name", unique = true, nullable = false)
    private String categoryName;

    @Column(name = "book_category_intro", nullable = false)
    private String categoryIntro;

    @JsonBackReference
    @ManyToMany
            (
                cascade = {
                        CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.REFRESH,
                        CascadeType.PERSIST
                },
                mappedBy = "bookCategoryInfos"
            )
    private List<BookInfo> bookInfos = new ArrayList<>();

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryIntro()
    {
        return categoryIntro;
    }

    public void setCategoryIntro(String categoryIntro)
    {
        this.categoryIntro = categoryIntro;
    }

    public List<BookInfo> getBookInfos()
    {
        return bookInfos;
    }

    public void setBookInfos(List<BookInfo> bookInfos)
    {
        this.bookInfos = bookInfos;
    }

    public void addBookInfo(BookInfo bookInfo)
    {
        bookInfos.add(bookInfo);
    }
}

