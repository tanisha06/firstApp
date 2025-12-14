package com.secondApp.SecondApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String city;

    public Student() {}

    public Student(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    // getters & setters (or Lombok)
}
