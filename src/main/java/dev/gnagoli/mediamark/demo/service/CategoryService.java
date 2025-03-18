package dev.gnagoli.mediamark.demo.service;


import dev.gnagoli.mediamark.demo.domain.CategoryEntity;
import org.apache.poi.ss.usermodel.Cell;
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

import static dev.gnagoli.mediamark.demo.utils.CSVUtils.readCell;

@Service
public class CategoryService {


    public List<CategoryEntity> readFromCsv() throws IOException {
        List<CategoryEntity> products = new ArrayList<>();

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
                products.add(categoryEntity);
            }

        }
        return products;
    }

}
