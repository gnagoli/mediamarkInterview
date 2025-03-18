package dev.gnagoli.mediamark.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CsvImportRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

//    private final ProductService productService;
//
//    public CsvImportRunner(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        List<ProductEntity> productList = productService.readFromCsv();
//        log.info("Read products from csv");
//        log.info("Read products from file {}", productList.size());
//        productList.forEach(product -> {
//            log.debug(product.toString());
//        });
//    }
}
