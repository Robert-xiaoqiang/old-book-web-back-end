package xiaoqiang.wang.modeldomain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_info")
public class OrderInfo implements Serializable {
    private final static long serialVersionID = 1l;
    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "order_timestamp", nullable = false)
    private Date orderTimestamp;

    @JsonBackReference
    @ManyToOne
            (
                    optional = false
            )
    @JoinColumn(name = "buyer_id")
    private UserInfo userInfo;



    @JsonManagedReference
    @OneToMany
            (
                    cascade = {
                            CascadeType.ALL
                    },
                    mappedBy = "orderInfo"
            )
    private List<OrderDetail> orderDetails;

    public long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

    public Date getOrderTimestamp()
    {
        return orderTimestamp;
    }

    public void setOrderTimestamp(Date orderTimestamp)
    {
        this.orderTimestamp = orderTimestamp;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public List<OrderDetail> getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetailAndSetOrderInfo(OrderDetail orderDetail)
    {
        orderDetails.add(orderDetail);
        orderDetail.setOrderInfo(this);
    }

}
