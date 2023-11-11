package com.academy.sirma.midexam;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.entities.Manager;
import com.academy.sirma.midexam.entities.StaffManager;
import com.academy.sirma.midexam.services.Service;
import com.academy.sirma.midexam.services.StaffService;

import java.io.*;
import java.util.Scanner;

public class StaffManagementApp {
    public static void main(String[] args) throws IOException {
        // implement fileReader/fileWriter to handle saving into csv/json
        BufferedReader reader = new BufferedReader(new FileReader(StringConstants.JSON_FILE_PATH));
        BufferedWriter writer = new BufferedWriter(new FileWriter(StringConstants.JSON_FILE_PATH));
        Service service = new StaffService(reader, writer);
        Manager manager = new StaffManager(service);
        System.out.println("Welcome to Staff Management System");
        displayCommands();
        boolean isRunning = true;

        Scanner sc = new Scanner(System.in);
        String command;
        while (isRunning) {
            System.out.println("Enter a command:");
            command = sc.nextLine();
            manager.execute(command);
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
