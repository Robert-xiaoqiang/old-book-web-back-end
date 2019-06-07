package xiaoqiang.wang.modeldomain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "book_info")
public class BookInfo {
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

}
