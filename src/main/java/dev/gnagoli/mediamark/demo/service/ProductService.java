package dev.gnagoli.mediamark.demo.service;


import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.demo.mapper.ProductMapper;
import dev.gnagoli.mediamark.demo.repository.ProductRepository;
import dev.gnagoli.mediamark.openapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    private Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    private ProductEntity saveProduct(Product product) {
        var productEntity = productMapper.toProductEntity(product);
        return productRepository.save(productEntity);
    }
}
