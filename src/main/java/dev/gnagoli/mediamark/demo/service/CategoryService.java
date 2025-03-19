package dev.gnagoli.mediamark.demo.service;


import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import dev.gnagoli.mediamark.demo.mapper.CategoryMapper;
import dev.gnagoli.mediamark.demo.repository.CategoryRepository;
import dev.gnagoli.mediamark.openapi.model.Category;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dev.gnagoli.mediamark.demo.utils.CSVUtils.readCell;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Delete existing categories
     *
     * @param categoryId the category Id
     */
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }


    /**
     * Read categories from csv
     *
     * @return the list of categories
     * @throws IOException
     */
    public List<CategoryEntity> readFromCsv() throws IOException {
        List<CategoryEntity> categoryEntities = new ArrayList<>();

        FileInputStream file = new FileInputStream(new File("CategoriesDataSet.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row cells : sheet) {
            CategoryEntity categoryEntity = new CategoryEntity();
            int rowIndex = cells.getRowNum();
            if (rowIndex < 1) {
                continue;
            }
            Iterator<Cell> cellIterator = cells.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                if (columnIndex != 0 && columnIndex != 1 && columnIndex != 2) {
                    continue;
                }
                switch (columnIndex) {
                    case 0:
                        var id = Optional.ofNullable(readCell(cell)).map(String::valueOf).map(Double::valueOf).map(Double::intValue).orElse(0);
                        categoryEntity.setId(id);
                        break;
                    case 1:
                        categoryEntity.setName(String.valueOf(readCell(cell)));
                        break;
                    case 2:
                        categoryEntity.setParentId(String.valueOf(readCell(cell)));
                        break;
                    default:
                        break;
                }
                categoryEntities.add(categoryEntity);
            }

        }
        categoryRepository.saveAll(categoryEntities);
        return categoryEntities;
    }

    /**
     * Get product categories
     *
     * @param productId the id of the product
     * @return the list of categories
     */
    public List<Category> getProductCategories(Long productId) {
        var categories = categoryRepository.findCategoryEntitiesByProductId(productId);
        return categories.parallelStream().map(categoryMapper::toCategory).collect(Collectors.toList());
    }

    /**
     * Get categories by id
     *
     * @param categoryId the id of the category
     * @return the category
     */
    public Optional<Category> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::toCategory);
    }


    /**
     * update existing categories
     *
     * @param category the category to update
     * @return updated category
     */
    public Category updateCategories(Category category) {
        var existingCategory = categoryRepository.findById(category.getCategoryId()).orElseThrow();
        categoryMapper.patchCategory(category, existingCategory);
        return categoryMapper.toCategory(categoryRepository.save(existingCategory));
    }

    /**
     * create categories
     *
     * @param category the category to create
     * @return
     */
    public Category save(Category category) {
        var saved = categoryRepository.save(categoryMapper.toEntity(category));
        return categoryMapper.toCategory(saved);
    }

    /**
     * Get a paginated list of categories
     *
     * @param page the current page
     * @param size the size of pages
     * @return the list of categories
     */
    public List<Category> getCategories(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size))
                .map(categoryMapper::toCategory)
                .getContent();
    }
}
