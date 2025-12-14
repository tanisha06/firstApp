package com.secondApp.SecondApp.repository;

import com.secondApp.SecondApp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
