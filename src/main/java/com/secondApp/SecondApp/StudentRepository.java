package com.secondApp.SecondApp;

import org.springframework.data.jpa.repository.JpaRepository;
// to use all jpa functionalities
public interface StudentRepository extends JpaRepository<Student, Long> {
}
