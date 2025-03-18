package dev.gnagoli.mediamark.demo.utils;

import org.apache.poi.ss.usermodel.Cell;

public class CSVUtils {

   public static Object readCell(Cell cell) {
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
