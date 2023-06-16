package com.example.springweb1.Employee;

public class Employee {
    private static int idGenerator = 1;

    private int id;
    private String name;
    private int salary;


    public Employee(String name, int salary) {
        this.id = idGenerator++;
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


}
