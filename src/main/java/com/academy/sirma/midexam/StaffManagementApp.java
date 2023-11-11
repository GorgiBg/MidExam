package com.academy.sirma.midexam;

import com.academy.sirma.midexam.entities.Manager;
import com.academy.sirma.midexam.entities.StaffManager;
import com.academy.sirma.midexam.services.Service;
import com.academy.sirma.midexam.services.StaffService;

import java.io.*;

public class StaffManagementApp {
    public static void main(String[] args) throws IOException {
// implement fileReader/fileWriter to handle saving into csv/json
        String path = "";
        FileReader reader = new FileReader(new File(path));
        FileWriter writer = new FileWriter(new File(path));
        String command = "";
        Service service = new StaffService(reader, writer);
        Manager manager = new StaffManager(service);
        System.out.println("Welcome to Staff Management System");
        displayCommands();
        boolean isRunning = true;
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

    private static void displayCommands() {
    }
}
