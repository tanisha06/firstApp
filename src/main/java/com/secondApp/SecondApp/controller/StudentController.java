package com.secondApp.SecondApp.controller;

import com.secondApp.SecondApp.entity.Student;
import com.secondApp.SecondApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repo;

//     Fetch ALL – JPQL
    @GetMapping("/jpql/all")
    public List<Student> getAllJPQL() {
        return repo.findAllJPQL();
    }

    // Fetch by NAME – JPQL
    @GetMapping("/jpql/{name}")
    public List<Student> getByNameJPQL(@PathVariable String name) {
        return repo.findByNameJPQL(name);
    }

    // Fetch ALL – Native
    @GetMapping("/native/all")
    public List<Student> getAllNative() {
        return repo.findAllNative();
    }

    // Fetch by NAME – Native
    @GetMapping("/native/{name}")
    public List<Student> getByNameNative(@PathVariable String name) {
        return repo.findByNameNative(name);
    }

// for update using id ::
    @PutMapping("/update-salary/{id}/{salary}")
    public String updateSalary(
            @PathVariable Long id,
            @PathVariable Double salary
    ) {
        int updated = repo.updateSalaryById(id, salary);

        if (updated == 0) {
            return "Student not found with id " + id;
        }

        return "Salary updated successfully";
    }
}
