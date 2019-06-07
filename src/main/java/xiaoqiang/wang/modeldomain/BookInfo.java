package xiaoqiang.wang.modeldomain;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

@Entity
@Table(name = "book_info")
public class BookInfo implements Serializable {
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "book_intro")
    private String bookIntro;

    @ManyToMany
            (
                    cascade = {
                            CascadeType.ALL
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

    public List<BookCategoryInfo> getBookCategoryInfos()
    {
        return bookCategoryInfos;
    }

    public void setBookCategoryInfos(List<BookCategoryInfo> bookCategoryInfos)
    {
        this.bookCategoryInfos = bookCategoryInfos;
    }
}
