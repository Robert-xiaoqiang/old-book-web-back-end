package xiaoqiang.wang.modeldomain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order_info")
public class OrderInfo implements Serializable {
    @Column(name = "order_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "order_timestamp", nullable = false)
    private Date orderTimestamp;

    @Column(name = "trade_place", nullable = false)
    private String tradePlace;

    @ManyToOne
            (
                    optional = false
            )
    @JoinColumn(name = "")
}
