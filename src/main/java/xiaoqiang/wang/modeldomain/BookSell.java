package xiaoqiang.wang.modeldomain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book_sell")
public class BookSell implements Serializable {
    private final static long serialVersionID = 1l;
    @Column(name = "book_sell_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @JsonBackReference
    @ManyToOne
            (
                    optional = false
            )
    @JoinColumn(name = "book_id")
    private BookInfo bookInfo;

    @JsonBackReference
    @ManyToOne
            (
                    optional = false
            )
    @JoinColumn(name = "seller_id")
    private UserInfo userInfo;

    @Column(name = "origin_price")
    private double originPrice;

    @Column(name = "sell_price")
    private double sellPrice;

    /**
     * null when OrderDetail is an empty table
     * Is is good design?
     */
    @JsonManagedReference
    @OneToOne
            (
                    cascade = {
                            CascadeType.ALL
                    },
                    mappedBy = "bookSell"
            )
    private OrderDetail orderDetail;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public BookInfo getBookInfo()
    {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo)
    {
        this.bookInfo = bookInfo;
    }

    public double getOriginPrice()
    {
        return originPrice;
    }

    public void setOriginPrice(double originPrice)
    {
        this.originPrice = originPrice;
    }

    public double getSellPrice()
    {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice)
    {
        this.sellPrice = sellPrice;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public OrderDetail getOrderDetail()
    {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail)
    {
        this.orderDetail = orderDetail;
    }
}
