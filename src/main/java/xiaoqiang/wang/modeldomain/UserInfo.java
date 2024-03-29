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

    @Column(name = "avatar_url", nullable = false)
    private String avatarURL;

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

    @JsonManagedReference
    @OneToMany
            (
                    cascade = {
                            CascadeType.ALL
                    },
                    mappedBy = "userInfo"
            )
    private List<UnreadMessage> unreadMessages = new ArrayList<>();

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

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
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

    public List<UnreadMessage> getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(List<UnreadMessage> unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    /**
     * @note setUserInfo is unnecessary
     * before adding, we have already created an object with the foreign key
     * @param bookSell
     */
    public void addBookSell(BookSell bookSell)
    {
        bookSells.add(bookSell);
    }

    public void addBookBuy(BookBuy bookBuy)
    {
        bookBuys.add(bookBuy);
    }

    public void addOrderInfo(OrderInfo orderInfo)
    {
        orderInfos.add(orderInfo);
    }

    public void addUnreadMessage(UnreadMessage unreadMessage)
    {
        unreadMessages.add(unreadMessage);
    }
}
