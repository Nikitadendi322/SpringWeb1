package com.example.springweb1.Repository;


import com.example.springweb1.Employee.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    @PostConstruct
    public void init() {
        employees.add(new Employee("Иван", 10_000));
        employees.add(new Employee("Катя", 30_000));
        employees.add(new Employee("Петя", 25_000));
    }

    public List<Employee> getAll() {
        return Collections.unmodifiableList(employees);
    }

    public Employee add(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public void update(int id, Employee employee) {
        int indexForUpdating = findIndexById(id);
        if (indexForUpdating != -1) {
            employees.set(indexForUpdating, employee);
        }
    }

    public Optional<Employee> findById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst();
    }

    public void delete(int id) {
        int indexForRemoving = findIndexById(id);
        if (indexForRemoving != -1) {
            employees.remove(indexForRemoving);
        }
    }

    private int findIndexById(int id) {
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;


    }

}

