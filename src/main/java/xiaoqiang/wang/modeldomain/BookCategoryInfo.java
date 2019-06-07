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
@Table(name = "book_category_info")
public class BookCategoryInfo {
    @Column(name = "book_category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "book_category_intro", nullable = false)
    private String intro;

    public long getId()
    {
        return id;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }
}
