package xiaoqiang.wang.modeldomain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "book_info")
public class BookInfo implements Serializable {
    private final static long serialVersionID = 1l;
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "book_intro")
    private String bookIntro;

    @Column(name = "book_intro_url")
    private String bookIntroURL;

    @Column(name = "book_image_url")
    private String bookImageURL;

    @JsonManagedReference
    @ManyToMany
            (
                    cascade = {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    }
            )
    @JoinTable
            (
                    name = "book_with_category",
                    joinColumns = {
                            @JoinColumn(name = "book_id")
                    },
                    inverseJoinColumns = {
                            @JoinColumn(name = "book_category_id")
                    }
            )
    private List<BookCategoryInfo> bookCategoryInfos = new ArrayList<>();

    @JsonBackReference
    @OneToMany
            (
                    cascade = {
                        CascadeType.ALL
                    },
                    mappedBy = "bookInfo"

            )
    private List<BookSell> bookSells = new ArrayList<>();

    @JsonBackReference
    @OneToMany
            (
                    cascade = {
                            CascadeType.ALL
                    },
                    mappedBy = "bookInfo"
            )
    private List<BookBuy> bookBuys = new ArrayList<>();

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookIntro()
    {
        return bookIntro;
    }

    public void setBookIntro(String bookIntro)
    {
        this.bookIntro = bookIntro;
    }

    public String getBookIntroURL() {
        return bookIntroURL;
    }

    public void setBookIntroURL(String bookIntroURL) {
        this.bookIntroURL = bookIntroURL;
    }

    public String getBookImageURL() {
        return bookImageURL;
    }

    public void setBookImageURL(String bookImageURL) {
        this.bookImageURL = bookImageURL;
    }

    public List<BookCategoryInfo> getBookCategoryInfos()
    {
        return bookCategoryInfos;
    }

    public void setBookCategoryInfos(List<BookCategoryInfo> bookCategoryInfos)
    {
        this.bookCategoryInfos = bookCategoryInfos;
    }

    public List<BookSell> getBookSells()
    {
        return bookSells;
    }

    public void setBookSells(List<BookSell> bookSells)
    {
        this.bookSells = bookSells;
    }

    public List<BookBuy> getBookBuys()
    {
        return bookBuys;
    }

    public void setBookBuys(List<BookBuy> bookBuys)
    {
        this.bookBuys = bookBuys;
    }


    
    public void addBookCategoryInfo(BookCategoryInfo bookCategoryInfo)
    {
        bookCategoryInfos.add(bookCategoryInfo);
    }

    public void addBookSell(BookSell bookSell)
    {
        bookSells.add(bookSell);
    }

    public void addBookBuy(BookBuy bookBuy)
    {
        bookBuys.add(bookBuy);
    }
}
