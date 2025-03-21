package dev.gnagoli.mediamark.demo.service;


import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.demo.mapper.ProductMapper;
import dev.gnagoli.mediamark.demo.repository.ProductRepository;
import dev.gnagoli.mediamark.openapi.model.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static dev.gnagoli.mediamark.demo.utils.CSVUtils.readCell;

@Service
@Transactional
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Find a product by id
     *
     * @param id the id of the product
     * @return the product
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toProduct);
    }

    public Product saveProduct(Product product) {
        var productEntity = productMapper.toProductEntity(product);
        return productMapper.toProduct(productRepository.save(productEntity));
    }


    /**
     * Read product data from csv
     *
     * @return the list of data
     * @throws IOException
     */
    public List<ProductEntity> readFromCsv() throws IOException {
        List<ProductEntity> products = new ArrayList<>();

        FileInputStream file = new FileInputStream("ProductsDataSet.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row cells : sheet) {
            ProductEntity productEntity = new ProductEntity();
            int rowIndex = cells.getRowNum();
            if (rowIndex < 1) {
                continue;
            }
            Iterator<Cell> cellIterator = cells.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0:
                        productEntity.setName(String.valueOf(readCell(cell)));
                        break;
                    case 1:
                        var value = String.valueOf(readCell(cell)).split(";");
                        var values = Arrays.stream(value).map(Double::valueOf).map(Double::intValue).toList();
                        productEntity.setRefCategory(new ArrayList<>(values));
                        break;
                    case 2:
                        productEntity.setOnlineStatus(String.valueOf(readCell(cell)));
                        break;
                    case 3:
                        productEntity.setLongDescription(String.valueOf(readCell(cell)));
                        break;
                    case 4:
                        productEntity.setShortDescription(String.valueOf(readCell(cell)));
                        break;
                    default:
                        break;
                }
                products.add(productEntity);
            }
        }
        productRepository.saveAll(products);
        return products;
    }

    /**
     * Delete an existing product by id
     *
     * @param productId the product id
     */
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    /**
     * Get product list from database
     *
     * @param page the current page
     * @param size the size of every page
     * @return the list of products
     */
    public List<Product> getProducts(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size)).map(productMapper::toProduct).getContent();
    }

    /**
     * Update an existing product
     *
     * @param product the product data
     * @return updated product
     */
    public Product updateProducts(Product product) {
        var existingProduct = productRepository.findById(product.getProductId()).orElseThrow();
        productMapper.patchProduct(product, existingProduct);
        return productMapper.toProduct(productRepository.save(existingProduct));
    }
}
