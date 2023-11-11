package com.academy.sirma.midexam.services;

import com.academy.sirma.midexam.entities.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Service {
    Map<Integer, Employee> readData(String path) throws IOException;

    void saveData(List<Employee> employees) throws IOException;
}
