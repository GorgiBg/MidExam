package com.academy.sirma.midexam.entities;

import java.util.Map;

public class Company {
    private Map<Integer, Employee> employees;

    public Company(Map<Integer, Employee> employees) {
        this.employees = employees;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }
}
