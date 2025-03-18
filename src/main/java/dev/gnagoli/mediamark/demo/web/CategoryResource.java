package dev.gnagoli.mediamark.demo.web;

import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import dev.gnagoli.mediamark.demo.service.CategoryService;
import dev.gnagoli.mediamark.openapi.api.CategoriesApi;
import dev.gnagoli.mediamark.openapi.api.CategoriesApiDelegate;
import dev.gnagoli.mediamark.openapi.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CategoryResource implements CategoriesApi {
    private final CategoryService CategoryService;

    public CategoryResource(CategoryService CategoryService) {
        this.CategoryService = CategoryService;
    }

    /**
     * @return
     */
    @Override
    public CategoriesApiDelegate getDelegate() {
        return CategoriesApi.super.getDelegate();
    }

    /**
     * DELETE /categories/{categoryId} : deleteCategory
     * deleteCategory
     *
     * @param categoryId the category id (required)
     * @return successful operation (status code 204)
     */
    @Override
    public ResponseEntity<Void> deleteCategory(String categoryId) {
        return CategoriesApi.super.deleteCategory(categoryId);
    }

    /**
     * GET /categories : get Categories list
     * endpoint to get categories list
     *
     * @param productId the product id (required)
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<List<Category>> getCategories(String productId) {
        return CategoriesApi.super.getCategories(productId);
    }

    /**
     * GET /categories/{categoryId} : get Category by id
     * endpoint to retreive category by id
     *
     * @param categoryId the product id (required)
     * @return successful operation (status code 200)
     * or product not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<Category> getCategory(String categoryId) {
        return CategoriesApi.super.getCategory(categoryId);
    }

    /**
     * PATCH /categories : updateProduct
     * endpoint to update an existing categories
     *
     * @param category (optional)
     * @return successful operation (status code 200)
     * or categories not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<Category> patchCategories(Category category) {
        return CategoriesApi.super.patchCategories(category);
    }

    /**
     * POST /categories : saveCategory
     * endpoint to save a new categories
     *
     * @param category (optional)
     * @return successful operation (status code 200)
     * or categorie not found (status code 400)
     * or Server Error (status code 500)
     */
    @Override
    public ResponseEntity<Category> postCategorie(Category category) {
        return CategoriesApi.super.postCategorie(category);
    }

    @GetMapping("categories/data")
    public List<CategoryEntity> fromCSV() throws IOException {
        return CategoryService.readFromCsv();
    }
}
