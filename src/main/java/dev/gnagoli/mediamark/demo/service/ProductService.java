package dev.gnagoli.mediamark.demo.service;


import dev.gnagoli.mediamark.demo.domain.ProductEntity;
import dev.gnagoli.mediamark.demo.mapper.ProductMapper;
import dev.gnagoli.mediamark.demo.repository.ProductRepository;
import dev.gnagoli.mediamark.openapi.model.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    DataFormatter fmt = new DataFormatter();

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity saveProduct(Product product) {
        var productEntity = productMapper.toProductEntity(product);
        return productRepository.save(productEntity);
    }


    public List<ProductEntity> readFromCsv() throws IOException {
        List<ProductEntity> products = new ArrayList<>();

        FileInputStream file = new FileInputStream(new File("ProductsDataSet.xlsx"));
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
                if (columnIndex != 0 && columnIndex != 1 && columnIndex != 3) {
                    continue;
                }
                String columnName = "";
                switch (columnIndex) {
                    case 0:
                        productEntity.setId(cell.getStringCellValue());
                        break;
                    case 1:
                        productEntity.setName(cell.getStringCellValue());
                        break;
                    case 3:
                        productEntity.setParentId(cell.getStringCellValue());
                        break;
                }
                products.add(productEntity);


            }

        }
        return products;
    }

    Object readCell(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

}
