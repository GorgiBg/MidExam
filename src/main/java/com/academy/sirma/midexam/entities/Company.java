package com.academy.sirma.midexam.entities;

import java.util.HashMap;
import java.util.Map;

public class Company {
    private Map<Integer, Employee> employees;

    public Company() {
        this.employees = new HashMap<>();
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }
}
