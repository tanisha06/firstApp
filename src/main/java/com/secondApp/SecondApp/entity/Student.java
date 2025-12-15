package com.secondApp.SecondApp.entity;

import jakarta.persistence.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String city;
    private Double salary;

    public Student() {
    }


    public Student(String name, int age, String city, Double salary) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.salary = salary;
    }


}
