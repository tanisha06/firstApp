package com.secondApp.SecondApp;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExcelController {

    // ===============================
    // POST endpoint to read uploaded Excel file
    // URL: http://localhost:9090/read-excel
    // This will accept an Excel file and return all data in JSON format
    // ===============================
    @PostMapping("/read-excel")
    public List<List<String>> readExcel(@RequestParam("file") MultipartFile file) {

        // Step 1: Create a list to store all rows of the Excel sheet
        List<List<String>> excelData = new ArrayList<>();

        try (
                // Step 2: Convert uploaded file into InputStream for Apache POI to read
                InputStream inputStream = file.getInputStream();
                // Step 3: Create a Workbook object to represent the Excel file (.xlsx)
                Workbook workbook = new XSSFWorkbook(inputStream)
        ) {

            // Step 4: Get the first sheet of the Excel file
            Sheet sheet = workbook.getSheetAt(0);

            // Step 5: Use DataFormatter to safely convert any type of cell (String, number, date, boolean) into a String
            DataFormatter formatter = new DataFormatter();

            // Step 6: Loop through all rows in the sheet
            for (Row row : sheet) {

                // Step 6a: Create a list to store one complete row
                List<String> rowData = new ArrayList<>();

                // Step 6b: Get total number of cells in the row
                int lastCellNum = row.getLastCellNum();

                // Step 6c: Loop through all cells in the row
                for (int cn = 0; cn < lastCellNum; cn++) {
                    // Get the cell at the current index
                    Cell cell = row.getCell(cn);

                    // Convert the cell value into String safely
                    String cellValue = formatter.formatCellValue(cell);

                    // Add the cell value to the row list
                    rowData.add(cellValue);
                }

                // Step 6d: Add the row list to the final Excel data list
                excelData.add(rowData);
            }

            // Step 7: Workbook will be automatically closed because of try-with-resources

        } catch (Exception e) {
            // Step 8: If there is any error (file not Excel, corrupted, etc.), print the stack trace
            e.printStackTrace();
        }

        // Step 9: Return the entire Excel data as JSON
        return excelData;
    }
}
