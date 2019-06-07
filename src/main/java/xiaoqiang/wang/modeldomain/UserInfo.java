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
}
