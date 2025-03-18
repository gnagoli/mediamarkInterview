package dev.gnagoli.mediamark.demo.utils;

import org.apache.poi.ss.usermodel.Cell;

public class CSVUtils {

    public static Object readCell(Cell cell) {
        Object value = "";
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            default:
                return value;
        }
        return value;
    }

}
