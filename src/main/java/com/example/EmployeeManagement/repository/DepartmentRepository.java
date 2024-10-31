package com.example.EmployeeManagement.repository;

import com.example.EmployeeManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
