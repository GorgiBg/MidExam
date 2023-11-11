package com.academy.sirma.midexam;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.entities.Employee;
import com.academy.sirma.midexam.entities.Manager;
import com.academy.sirma.midexam.entities.StaffManager;
import com.academy.sirma.midexam.services.Service;
import com.academy.sirma.midexam.services.StaffService;

import java.io.*;
import java.util.List;

public class StaffManagementApp {
    public static void main(String[] args) throws IOException {
// implement fileReader/fileWriter to handle saving into csv/json
        BufferedReader reader = new BufferedReader(new FileReader(StringConstants.JSON_FILE_PATH));
        BufferedWriter writer = new BufferedWriter(new FileWriter(StringConstants.JSON_FILE_PATH));
        String command = "";
        Service<Employee> service = new StaffService<>(reader, writer);
        Manager manager = new StaffManager(service);
        System.out.println("Welcome to Staff Management System");
        displayCommands();
        boolean isRunning = true;
        // testIfWriteAndReadSuccessfully(service);


        while (isRunning) {
            manager.execute(command);
// Add Employee
// 1, Peter Peterson, IT, Junior Java Developer, 1400.50
// Add Employee
// 2, Ivan Ivanson, IT, Junior Front-End Developer, 1400.00
// Edit 1
// 1, Peter Peterson, IT, Java Developer, 2500.00
// List employees
// Search Department Marketing
// Search Id 1
// Fire 1
// Search Name Peter
// Save &amp; Exit
        }
    }

    private static void testIfWriteAndReadSuccessfully(Service<Employee> service) throws IOException {
        Employee employee = new Employee(1, "G", "1980-05-20", "IT", "Junior Developer", 2000.15);
        service.saveData(List.of(employee));
        List<Employee> employees = service.readData(StringConstants.JSON_FILE_PATH);
        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }
    }

    private static void displayCommands() {
    }
}
