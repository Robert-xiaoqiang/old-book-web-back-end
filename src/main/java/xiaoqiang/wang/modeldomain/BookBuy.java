package xiaoqiang.wang.modeldomain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_buy")
public class BookBuy implements Serializable {
    private final static long serialVersionID = 1l;
    @Column(name = "book_buy_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne
            (
                    optional = false
            )
    @JoinColumn(name = "book_id")
    private BookInfo bookInfo;

    @ManyToOne
            (
                    optional = false
            )
    @JoinColumn(name = "buyer_id")
    private UserInfo userInfo;

    @Column(name = "lower_price")
    private double lowerPrice;

    @Column(name = "upper_price")
    private double upperPrice;

    public long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

    public void setBookInfo(BookInfo bookInfo)
    {
        this.bookInfo = bookInfo;
    }

    public BookInfo getBookInfo()
    {
        return bookInfo;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public double getLowerPrice()
    {
        return lowerPrice;
    }

    public void setLowerPrice(double lowerPrice)
    {
        this.lowerPrice = lowerPrice;
    }

    public double getUpperPrice()
    {
        return upperPrice;
    }

    public void setUpperPrice(double upperPrice)
    {
        this.upperPrice = upperPrice;
    }

}
