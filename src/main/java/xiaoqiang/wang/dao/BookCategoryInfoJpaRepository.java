package xiaoqiang.wang.dao;

import xiaoqiang.wang.modeldomain.BookCategoryInfo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryInfoJpaRepository extends JpaRepository<BookCategoryInfo, Long> {
    public Optional<BookCategoryInfo> findByCategoryName(String categoryName);
    public Optional<BookCategoryInfo> findById(Long id);
}
