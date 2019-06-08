package xiaoqiang.wang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xiaoqiang.wang.modeldomain.BookBuy;

public interface BookBuyJpaRepository extends JpaRepository<BookBuy, Long> {

}
