package com.academy.sirma.midexam.entities;

import com.academy.sirma.midexam.services.Service;

import java.util.Scanner;

public class StaffManager implements Manager{

    private final Service service;
    private final Scanner scanner;

    public StaffManager(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute(String command) {
        switch (command.toUpperCase().trim()) {
            case "ADD EMPLOYEE":
                //addEmployee();
                break;
            case "EDIT":
                //editEmployee();
                break;
            case "FIRE":
                //fireEmployee();
                break;
            case "SEARCH ID":
                //searchId();
                break;
            case "SEARCH DEPARTMENT":
                //searchDepartment();
                break;
            case "SEARCH NAME":
                //searchName();
                break;
            case "LIST EMPLOYEES":
                //listEmployees();
                break;
            case "SAVE & EXIT":
                //saveAndExit();
                break;
            default:
                System.out.println("Invalid command");
        }
    }
}
