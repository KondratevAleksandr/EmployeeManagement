package com.example.EmployeeManagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String position;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
