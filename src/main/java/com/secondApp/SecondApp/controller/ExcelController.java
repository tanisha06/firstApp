package com.secondApp.SecondApp.controller;

import com.secondApp.SecondApp.entity.Student;
import com.secondApp.SecondApp.repository.StudentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private StudentRepository repo;

    // ONLY Excel upload logic
    @PostMapping(
            value = "/save-excel",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String saveExcelToDatabase(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "File is empty";
        }

        List<Student> students = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {

                // Skip header row :: as we have written column name there
                if (row.getRowNum() == 0) continue;

                String name = formatter.formatCellValue(row.getCell(0)).trim();
                String ageText = formatter.formatCellValue(row.getCell(1)).trim();
                String city = formatter.formatCellValue(row.getCell(2)).trim();
                String salaryText = formatter.formatCellValue(row.getCell(3)).trim();

                if (name.isEmpty()) continue;

                int age = ageText.isEmpty() ? 0 : Integer.parseInt(ageText);
                Double salary = salaryText.isEmpty() ? 0.0 : Double.parseDouble(salaryText);


                students.add(new Student(name, age, city, salary));
            }

            repo.saveAll(students);

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        return "Saved " + students.size() + " records successfully";
    }
}
