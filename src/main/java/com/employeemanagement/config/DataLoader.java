package com.employeemanagement.config;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        if (employeeRepository.count() == 0) {
            Employee e1 = new Employee(
                    "John", "Doe", "john.doe@example.com", "+1-555-0101",
                    "Engineering", "Senior Software Engineer",
                    new BigDecimal("95000.00"), LocalDate.of(2020, 3, 15),
                    "123 Main St, New York, NY"
            );

            Employee e2 = new Employee(
                    "Jane", "Smith", "jane.smith@example.com", "+1-555-0102",
                    "Human Resources", "HR Manager",
                    new BigDecimal("75000.00"), LocalDate.of(2019, 7, 22),
                    "456 Oak Ave, Los Angeles, CA"
            );

            Employee e3 = new Employee(
                    "Michael", "Johnson", "michael.j@example.com", "+1-555-0103",
                    "Finance", "Financial Analyst",
                    new BigDecimal("68000.00"), LocalDate.of(2021, 1, 10),
                    "789 Pine Rd, Chicago, IL"
            );

            Employee e4 = new Employee(
                    "Emily", "Williams", "emily.w@example.com", "+1-555-0104",
                    "Marketing", "Marketing Director",
                    new BigDecimal("110000.00"), LocalDate.of(2018, 5, 30),
                    "321 Elm St, Boston, MA"
            );

            Employee e5 = new Employee(
                    "David", "Brown", "david.b@example.com", "+1-555-0105",
                    "Engineering", "DevOps Engineer",
                    new BigDecimal("88000.00"), LocalDate.of(2022, 9, 1),
                    "654 Cedar Ln, Seattle, WA"
            );

            employeeRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5));
            System.out.println("Sample employees data loaded successfully!");
        }
    }
}
