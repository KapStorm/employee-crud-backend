package com.kappa.backend.service;

import com.kappa.backend.entities.Employee;
import com.kappa.backend.repositories.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public Optional<Employee> getOneEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void deleteEmloyee(Long id) {
        employeeRepo.deleteById(id);
    }

    public void updateEmployeee(Employee employee) {
        employeeRepo.save(employee);
    }

}
