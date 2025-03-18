package dev.gnagoli.mediamark.demo.repository;

import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import dev.gnagoli.mediamark.openapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

//    @Query(value = "SELECT C FROM CategoryEntity C INNER JOIN ProductEntity P ON C.id IN (P.refCategory) " )
    List<Category> findCategoryEntitiesByProductId(long pr);
}
