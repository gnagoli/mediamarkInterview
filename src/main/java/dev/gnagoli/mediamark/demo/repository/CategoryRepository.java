package dev.gnagoli.mediamark.demo.repository;

import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

//    @Query(value = "SELECT * FROM ", nativeQuery = true )
//    List<Category> findCategoryEntitiesByProductId(long pr);
}
