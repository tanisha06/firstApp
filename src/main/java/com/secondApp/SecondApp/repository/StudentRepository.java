
package com.secondApp.SecondApp.repository;


import com.secondApp.SecondApp.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    Fetch ALL students (JPQL)
    @Query("SELECT s FROM Student s")
    List<Student> findAllJPQL();

    // Fetch by name (JPQL)
    @Query("SELECT s FROM Student s WHERE s.name = :name")
    List<Student> findByNameJPQL(@Param("name") String name);

    //Fetch ALL students (NATIVE SQL)
    @Query(value = "SELECT * FROM students", nativeQuery = true)
    List<Student> findAllNative();

    // Fetch by name (NATIVE SQL)
    @Query(value = "SELECT * FROM students WHERE name = :name", nativeQuery = true)
    List<Student> findByNameNative(@Param("name") String name);


    // update by id : salary::
    @Modifying
    @Transactional
    @Query(
            value = "UPDATE students SET salary = :salary WHERE id = :id",
            nativeQuery = true
    )
    int updateSalaryById(
            @Param("id") Long id,
            @Param("salary") Double salary
    );


}
