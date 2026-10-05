package com.employeemanagement.controller;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management", description = "Employee Management REST API endpoints")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieves an employee by their ID")
    public ResponseEntity<Employee> getEmployeeById(
            @Parameter(description = "Employee ID", required = true)
            @PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    @Operation(summary = "Create new employee", description = "Creates a new employee record")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee", description = "Updates an existing employee record")
    public ResponseEntity<Employee> updateEmployee(
            @Parameter(description = "Employee ID", required = true)
            @PathVariable Long id,
            @Valid @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee", description = "Deletes an employee record")
    public ResponseEntity<Map<String, Object>> deleteEmployee(
            @Parameter(description = "Employee ID", required = true)
            @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{department}")
    @Operation(summary = "Get employees by department", description = "Retrieves employees by department name")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(
            @Parameter(description = "Department name", required = true)
            @PathVariable String department) {
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search")
    @Operation(summary = "Search employees by last name", description = "Searches employees by partial last name match")
    public ResponseEntity<List<Employee>> searchEmployeesByLastName(
            @Parameter(description = "Last name (partial match)", required = true)
            @RequestParam String lastName) {
        List<Employee> employees = employeeService.searchEmployeesByLastName(lastName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/salary-range")
    @Operation(summary = "Get employees by salary range", description = "Retrieves employees within a salary range")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(
            @Parameter(description = "Minimum salary", required = true)
            @RequestParam BigDecimal minSalary,
            @Parameter(description = "Maximum salary", required = true)
            @RequestParam BigDecimal maxSalary) {
        List<Employee> employees = employeeService.getEmployeesBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get employee by email", description = "Retrieves an employee by their email address")
    public ResponseEntity<Employee> getEmployeeByEmail(
            @Parameter(description = "Employee email", required = true)
            @PathVariable String email) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(employee);
    }
}
