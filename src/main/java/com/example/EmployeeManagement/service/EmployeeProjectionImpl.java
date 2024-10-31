package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.entity.Employee;

public class EmployeeProjectionImpl implements EmployeeProjection {
    private String fullName;
    private String position;
    private String department;

    public EmployeeProjectionImpl(Employee employee) {
        this.fullName = employee.getFirstName() + " " + employee.getLastName();
        this.position = employee.getPosition();
        this.department = employee.getDepartment() != null ? employee.getDepartment().getName() : null;
    }


    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public String getDepartmentName() {
        return department;
    }
}
