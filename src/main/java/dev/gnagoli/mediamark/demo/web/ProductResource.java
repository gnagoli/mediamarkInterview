package dev.gnagoli.mediamark.demo.web;

import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.demo.service.ProductService;
import dev.gnagoli.mediamark.openapi.api.ProductsApi;
import dev.gnagoli.mediamark.openapi.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductResource implements ProductsApi {
    private final ProductService productService;


    public ProductResource(ProductService productService) {
        this.productService = productService;
    }


    /**
     * GET /products : get Product list
     * endpoint to get product list
     *
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<List<Product>> getProducts() {
        return ProductsApi.super.getProducts();
    }

    /**
     * PATCH /products : updateProduct
     * endpoint to update an existing product
     *
     * @param product (optional)
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<Product> patchProducts(Product product) {
        return ProductsApi.super.patchProducts(product);
    }

    /**
     * POST /products : saveProduct
     * endpoint to save a new product
     *
     * @param product (optional)
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<Product> postProducts(Product product) {
        return ProductsApi.super.postProducts(product);
    }

    @GetMapping("products/data")
    public List<ProductEntity> fromCSV() throws IOException {
        return productService.readFromCsv();
    }
}
