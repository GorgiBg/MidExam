package com.academy.sirma.midexam;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.entities.Employee;
import com.academy.sirma.midexam.entities.Manager;
import com.academy.sirma.midexam.entities.StaffManager;
import com.academy.sirma.midexam.services.Service;
import com.academy.sirma.midexam.services.StaffService;

import java.io.*;
import java.util.List;
import java.util.Map;

public class StaffManagementApp {
    public static void main(String[] args) throws IOException {
// implement fileReader/fileWriter to handle saving into csv/json
        BufferedReader reader = new BufferedReader(new FileReader(StringConstants.JSON_FILE_PATH));
        BufferedWriter writer = new BufferedWriter(new FileWriter(StringConstants.JSON_FILE_PATH));
        String command = "";
        Service service = new StaffService(reader, writer);
        Manager manager = new StaffManager(service);
        System.out.println("Welcome to Staff Management System");
        displayCommands();
        boolean isRunning = true;
        //testIfWriteAndReadSuccessfully(service);


        while (isRunning) {
            //manager.execute(command);
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

    private static void testIfWriteAndReadSuccessfully(Service service) throws IOException {
        Employee employee = new Employee(1, "G", "1980-05-20", "IT", "Junior Developer", 2000.15);
        service.saveData(List.of(employee));
        Map<Integer, Employee> employees = service.readData(StringConstants.JSON_FILE_PATH);
        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue().toString());
        }


    }

    private static void displayCommands() {
        System.out.println("*** Commands Available ***");
        System.out.println("1. Add Employee: Input employee details and add them to the system.");
        System.out.println("2. Edit Employee: Modify existing employee details.");
        System.out.println("3. Fire Employee: Flag that the employee does’t work at the company anymore.");
        System.out.println("4. Search Id: Search for an employee by ID.");
        System.out.println("5. Search Department: Search for employees in a specific department.");
        System.out.println("6. Search Name: Search for an employee by name.");
        System.out.println("7. List Employees: Display a list of all active employees with their details.");
        System.out.println("8. Save & Exit: Save changes and exit the system.");
    }
}
