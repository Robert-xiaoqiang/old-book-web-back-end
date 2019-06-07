package xiaoqiang.wang.dao;

import xiaoqiang.wang.modeldomain.BookInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInfoJpaRepository extends JpaRepository<BookInfo, Long> {

}
