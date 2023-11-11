package com.academy.sirma.midexam.entities;

public class Employee {
    private int id;
    private String name;
    private String startDate;
    private String department;
    private String role;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, String startDate, String department, String role, double salary) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.department = department;
        this.role = role;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", startDate='" + startDate + '\'' +
            ", department='" + department + '\'' +
            ", role='" + role + '\'' +
            ", salary=" + salary +
            '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }
}