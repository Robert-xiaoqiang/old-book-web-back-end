package xiaoqiang.wang.modeldomain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    private final static long serialVersionID = 1l;

    @Column(name = "order_detail_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @JsonBackReference
    @ManyToOne
            (
                    optional = false
            )
    @JoinColumn(name = "order_id")
    private OrderInfo orderInfo;

    // many to one
    // std::decay<>()::type
    @JsonBackReference
    @OneToOne
            (
                    optional = false
            )
    @JoinColumn(name = "book_sell_id", unique = true)
    private BookSell bookSell;

    @Column(name = "trade_place", nullable = false)
    private String tradePlace;

    @Column(name = "trade_timestamp")
    private Date tradeTimestamp;

    public long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

    public Date getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradeTimestamp(Date tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }

    public String getTradePlace() {
        return tradePlace;
    }

    public void setTradePlace(String tradePlace) {
        this.tradePlace = tradePlace;
    }

    public BookSell getBookSell()
    {
        return bookSell;
    }

    public void setBookSell(BookSell bookSell)
    {
        this.bookSell = bookSell;
    }

    public OrderInfo getOrderInfo()
    {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo)
    {
        this.orderInfo = orderInfo;
    }
}
