package xiaoqiang.wang.modeldomain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {
    private final static long serialVersionID = 1l;
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "pass_word")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonManagedReference
    @OneToMany
            (
                    cascade = {
                            CascadeType.ALL
                    },
                    mappedBy = "userInfo"
            )
    private List<BookSell> bookSells = new ArrayList<>();

    @JsonManagedReference
    @OneToMany
            (
                    cascade = {
                            CascadeType.ALL
                    },
                    mappedBy = "userInfo"
            )
    private List<BookBuy> bookBuys = new ArrayList<>();

    @JsonManagedReference
    @OneToMany
            (
                    cascade = {
                            CascadeType.ALL
                    },
                    mappedBy = "userInfo"
            )
    private List<OrderInfo> orderInfos = new ArrayList<>();

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public List<OrderInfo> getOrderInfos()
    {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos)
    {
        this.orderInfos = orderInfos;
    }

    public void addBookSellAndSetUserInfo(BookSell bookSell)
    {
        bookSells.add(bookSell);
        bookSell.setUserInfo(this);
    }

    public void addBookBuyAndSetUserInfo(BookBuy bookBuy)
    {
        bookBuys.add(bookBuy);
        bookBuy.setUserInfo(this);
    }

    public void addOrderInfoAndSetUserInfo(OrderInfo orderInfo)
    {
        orderInfos.add(orderInfo);
        orderInfo.setUserInfo(this);
    }
}
