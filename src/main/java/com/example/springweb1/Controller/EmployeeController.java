package com.example.springweb1.Controller;


import com.example.springweb1.Employee.Employee;
import com.example.springweb1.Service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/salary/sum")
    public int getSumOfSalaries() {
        return employeeService.getSumOfSalaries();
    }

    @GetMapping("/salary/min")
    public Employee getEmployeeWithMinSalary() {
        return employeeService.getEmployeeWithMinSalary();
    }

    @GetMapping("/salary/max")
    public Employee getEmployeeWithMaxSalary() {
        return employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("/high-salary")
    public List<Employee> getEmployeeWithSalaryHigherThanAverage() {
        return employeeService.getEmployeeWithSalaryHigherThanAverage();
    }

    @PostMapping
    public List<Employee> createBatch(@RequestBody List<Employee> employees) {
        return employeeService.createBatch(employees);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Employee employee) {
        employeeService.update(id, employee);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable int id) {
        return employeeService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        employeeService.delete(id);
    }

    @GetMapping("/salaryHigherThan")
    public List<Employee> getEmployeeWithSalaryHigherThan(@RequestParam int salary) {
        return employeeService.getEmployeeWithSalaryHigherThan(salary);
    }


}

