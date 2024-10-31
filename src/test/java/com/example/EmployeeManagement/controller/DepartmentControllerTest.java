package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.entity.Department;
import com.example.EmployeeManagement.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturn_DepartmentById_WhenExists() {
        Department department = new Department();
        department.setId(1);
        department.setName("HR");

        when(departmentService.findById(1)).thenReturn(department);

        ResponseEntity<Department> response = departmentController.getDepartmentById(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(department, response.getBody());
    }

    @Test
    public void shouldReturn_NotFound_WhenDepartmentByIdDoesNotExist() {
        when(departmentService.findById(1)).thenReturn(null);

        ResponseEntity<Department> response = departmentController.getDepartmentById(1);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void shouldReturn_AllDepartments() {
        Department department1 = new Department();
        department1.setId(1);
        department1.setName("HR");

        Department department2 = new Department();
        department2.setId(2);
        department2.setName("IT");

        when(departmentService.findAll()).thenReturn(Arrays.asList(department1, department2));

        List<Department> response = departmentController.getAllDepartments();
        assertEquals(2, response.size());
    }

    @Test
    public void shouldReturn_CreatedDepartment_WhenDepartmentIsSaved() {
        Department department = new Department();
        department.setName("HR");

        when(departmentService.save(any(Department.class))).thenReturn(department);

        Department response = departmentController.createDepartment(department);
        assertEquals(department, response);
        verify(departmentService, times(1)).save(department);
    }

    @Test
    public void shouldReturn_UpdatedDepartment_WhenDepartmentExists() {
        Department existingDepartment = new Department();
        existingDepartment.setId(1);
        existingDepartment.setName("HR");

        Department updatedDepartment = new Department();
        updatedDepartment.setName("Finance");

        when(departmentService.updateDepartment(1, updatedDepartment)).thenReturn(existingDepartment);

        ResponseEntity<Department> response = departmentController.updateDepartment(1, updatedDepartment);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(existingDepartment, response.getBody());
    }

    @Test
    public void shouldReturn_NotFound_WhenUpdatingDepartmentThatDoesNotExist() {
        Department updatedDepartment = new Department();
        updatedDepartment.setName("Finance");

        when(departmentService.updateDepartment(1, updatedDepartment)).thenReturn(null);

        ResponseEntity<Department> response = departmentController.updateDepartment(1, updatedDepartment);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void shouldReturn_NoContent_WhenDeletingDepartment() {
        ResponseEntity<Void> response = departmentController.deleteDepartment(1);
        assertEquals(204, response.getStatusCodeValue());
        verify(departmentService, times(1)).deleteById(1);
    }
}
