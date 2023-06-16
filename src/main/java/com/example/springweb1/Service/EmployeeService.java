package com.example.springweb1.Service;


import com.example.springweb1.Employee.Employee;
import com.example.springweb1.Repository.EmployeeRepository;
import com.example.springweb1.exception.EmployeeNotFoundException;
import com.example.springweb1.exception.EmployeeNotValidException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public int getSumOfSalaries() {
        return employeeRepository.getAll().stream().mapToInt(Employee::getSalary).sum();
    }

    public Employee getEmployeeWithMinSalary() {
        return employeeRepository.getAll().stream().min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }

    public Employee getEmployeeWithMaxSalary() {
        return employeeRepository.getAll().stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }

    public List<Employee> getEmployeeWithSalaryHigherThanAverage() {
        double average = employeeRepository.getAll().stream().mapToInt(Employee::getSalary).average().orElse(00);
        return getEmployeeWithSalaryHigherThan(average);
    }

    public List<Employee> createBatch(List<Employee> employees) {
        Optional<Employee> incorrectEmployee = employees.stream()
                .filter(employee -> employee.getSalary() <= 0 || employee.getName() == null
                        || employee.getName().isEmpty())
                .findFirst();
        if (incorrectEmployee.isPresent()) {
            throw new EmployeeNotValidException(incorrectEmployee.get());
        }
        return employees.stream()
                .map(employee -> new Employee(employee.getName(), employee.getSalary()))
                .map(employeeRepository::add)
                .collect(Collectors.toList());
    }

    public void update(int id, Employee employee) {
        Employee oldEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        oldEmployee.setSalary(employee.getSalary());
        oldEmployee.setName(employee.getName());
        employeeRepository.update(id, oldEmployee);
    }

    public Employee get(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void delete(int id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(id);

    }

    public List<Employee> getEmployeeWithSalaryHigherThan(double salary) {
        return employeeRepository.getAll().stream()
                .filter(employee -> employee.getSalary() > salary)
                .collect(Collectors.toList());
    }
}

