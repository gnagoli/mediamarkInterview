package dev.gnagoli.mediamark.demo;

import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CsvImportRunner implements ApplicationRunner {
    private final ProductService productService;

    public CsvImportRunner(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<ProductEntity> productList = productService.readFromCsv();
        log.info("Read products from csv");
        log.info("Read products from file {}", productList.size());
        productList.forEach(product -> {
            log.debug(product.toString());
        });
    }
}
