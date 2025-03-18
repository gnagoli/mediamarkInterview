package dev.gnagoli.mediamark.demo.repository;

import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
