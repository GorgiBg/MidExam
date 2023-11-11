package com.academy.sirma.midexam.entities;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.services.Service;

import java.util.Scanner;

public class StaffManager implements Manager{

    private final Service service;
    private final Scanner scanner;

    private final Company company;

    public StaffManager(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
        this.company = new Company();
    }

    @Override
    public void execute(String command) {
        String[] commandLine = command.split("\\s+");
        String toExecute = commandLine[0];
        switch (toExecute.toUpperCase().trim()) {
            case "ADD":
                addEmployee();
                break;
            case "EDIT":
                editEmployee(commandLine[1]);
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

    private void editEmployee(String inputId) {
        try {
            int employeeId = Integer.parseInt(inputId);
            if (!company.getEmployees().containsKey(employeeId)) {
                System.out.println("Employee with id " + employeeId + " not found!");
                return;
            }
            Employee current = company.getEmployees().get(employeeId);
            System.out.println("Employee details:");
            System.out.println(current.toString());
            System.out.println("Enter new details for the employee in the format: ID, Name, Department, Role, Salary:");
            String[] editData = scanner.nextLine().split(", ", 5);

            if (editData.length != 5) {
                System.out.println(StringConstants.INVALID_INPUT_NUMBER_FORMAT);
                return;
            }

            current.setName(editData[1].trim());
            current.setDepartment(editData[2].trim());
            current.setRole(editData[3].trim());
            try {
                int id = Integer.parseInt(editData[0].trim());
                current.setId(id);
                double newSalary = Double.parseDouble(editData[4].trim());
                current.setSalary(newSalary);
            } catch (NumberFormatException e) {
                System.out.println(StringConstants.INVALID_INPUT_NUMBER_FORMAT);
                return;
            }

            System.out.println("Employee details updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric value for Employee ID.");
        }
    }

    private void addEmployee() {
        System.out.println("Enter employee details in the format: ID, Name, Department, Role, Salary");
        System.out.print("Employee: ");
        String[] employeeDetails = scanner.nextLine().split(", ");
        if (employeeDetails.length != 5) {
            System.out.println("Invalid input format. Please enter all details.");
            return;
        }

        try {
            int id = Integer.parseInt(employeeDetails[0]);
            String name = employeeDetails[1];
            String department = employeeDetails[2];
            String role = employeeDetails[3];
            double salary = Double.parseDouble(employeeDetails[4]);
            Employee newEmployee = new Employee(id, name, department, role, salary);
            company.getEmployees().put(id, newEmployee);
            System.out.println("Employee added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid values for ID and Salary.");
        }
    }
}
