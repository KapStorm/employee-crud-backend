package com.kappa.backend.controller;

import com.kappa.backend.entities.Employee;
import com.kappa.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getOneEmployee(@PathVariable("id") Long id) {
        Optional<Employee> employee = employeeService.getOneEmployeeById(id);

        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmloyee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        Optional<Employee> temp = employeeService.getOneEmployeeById(employee.getId());

        if (!temp.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeService.updateEmployeee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
