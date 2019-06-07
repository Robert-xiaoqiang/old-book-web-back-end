package xiaoqiang.wang.dao;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryInfoJpaRepository extends JpaRepository<BookCategoryInfo, Long> {
    public List<BookCategoryInfo> findByCategoryName(String categoryName);
}
