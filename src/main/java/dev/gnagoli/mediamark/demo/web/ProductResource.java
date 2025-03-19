package dev.gnagoli.mediamark.demo.web;

import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.demo.service.CategoryService;
import dev.gnagoli.mediamark.demo.service.ProductService;
import dev.gnagoli.mediamark.openapi.api.ProductsApi;
import dev.gnagoli.mediamark.openapi.model.Category;
import dev.gnagoli.mediamark.openapi.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductResource implements ProductsApi {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductResource(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * DELETE /products/{productId} : deleteProduct
     * deleteProduct
     *
     * @param productId the product id (required)
     * @return successful operation (status code 204)
     */
    @Override
    public ResponseEntity<Void> deleteProducts(Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

    /**
     * GET /products/{productId} : get Product by id
     * endpoint to retreive product by id
     *
     * @param productId the product id (required)
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<Product> getProduct(Long productId) {
        var product = productService.getProductById(productId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /products/{productId}/categorie : get categories by product id
     * endpoint to retreive product by id
     *
     * @param productId the product id (required)
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<List<Category>> getProductCategory(Long productId) {
       var categories = categoryService.getProductCategories(productId);
        return ResponseEntity.ok(categories);
    }

    /**
     * GET /products : get Product list
     * endpoint to get product list
     *
     * @param page the current page (required)
     * @param size the current page (required)
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<List<Product>> getProducts(Integer page, Integer size) {
        return ResponseEntity.ok(productService.getProducts(page, size));
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
        return ResponseEntity.ok(productService.updateProducts(product));
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
        return ResponseEntity.ok(productService.saveProduct(product));
    }


    @GetMapping("products/data")
    public List<ProductEntity> fromCSV() throws IOException {
        return productService.readFromCsv();
    }
}
