package com.example.springweb1.exception;


import com.example.springweb1.Employee.Employee;

public class EmployeeNotValidException extends RuntimeException {

    private final Employee employee;

    public EmployeeNotValidException(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
