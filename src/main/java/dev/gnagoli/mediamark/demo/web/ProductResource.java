package dev.gnagoli.mediamark.demo.web;

import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.demo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductResource {
    private final ProductService productService;


    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/data")
    public List<ProductEntity> fromCSV() throws IOException {
        return productService.readFromCsv();
    }
}
