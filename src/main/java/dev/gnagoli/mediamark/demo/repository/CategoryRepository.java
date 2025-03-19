package dev.gnagoli.mediamark.demo.repository;

import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {


    @Query(value = """ 
            select *
                        from categories
                        where categories.id in (select ref_category
                                                from products
                                                         left join public.ref_category_table rct on products.id = rct.product_entity_id
                                              where product_entity_id = ?1)
            """, nativeQuery = true)
    List<CategoryEntity> findCategoryEntitiesByProductId(Long productId);
}
