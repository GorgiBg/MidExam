package com.academy.sirma.midexam.entities;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.services.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StaffManager implements Manager {

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
                fireEmployee(commandLine[1]);
                break;
            case "SEARCH":
                switch (commandLine[1]) {
                    case "Id" -> searchId(commandLine[2]);
                    case "Department" -> searchDepartment(commandLine[2]);
                    case "Name" -> searchName(commandLine[2]);
                    default -> System.out.println("Incorrect choice for search criteria.");
                }
                break;
            case "LIST":
                listEmployees();
                break;
            case "SAVE":
                saveAndExit();
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private void saveAndExit() {
        try {
            //get the list of employees from the map values set and saves them
            service.saveData(new ArrayList<>(company.getEmployees().values()));
            System.out.println("Data saved successfully. Exiting...");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error saving data. Please check the file and try again.");
        }
    }

    private void listEmployees() {
        Map<Integer, Employee> employees = company.getEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees in the company.");
        } else {
            System.out.println("List of all employees:");
            employees.values().forEach(System.out::println);
        }
    }

    private void searchName(String name) {
        // Filter employees by first name
        List<Employee> employeesWithName = company.getEmployees().values().stream()
            .filter(employee -> employee.getName().split("\\s+")[0].equalsIgnoreCase(name))
            .toList();

        if (employeesWithName.isEmpty()) {
            System.out.println("No employees found with the name: " + name);
        } else {
            System.out.println("Employees with the name " + name + ":");
            employeesWithName.forEach(System.out::println);
        }
    }

    private void searchDepartment(String department) {
        // Filter employees by department
        List<Employee> employeesInDepartment = company.getEmployees().values().stream()
            .filter(employee -> employee.getDepartment().equalsIgnoreCase(department))
            .toList();

        if (employeesInDepartment.isEmpty()) {
            System.out.println("No employees found in department: " + department);
        } else {
            System.out.println("Employees in department " + department + ":");
            employeesInDepartment.forEach(System.out::println);
        }
    }

    private void searchId(String inputId) {
        try {
            int employeeId = Integer.parseInt(inputId);
            if (!company.getEmployees().containsKey(employeeId)) {
                System.out.println("Employee not found with ID: " + employeeId);
            } else {
                Employee foundEmployee = company.getEmployees().get(employeeId);
                System.out.println("Employee found with ID " + employeeId + ":");
                System.out.println(foundEmployee);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer value for Employee ID.");
        }
    }

    private void fireEmployee(String inputId) {
        try {
            int employeeId = Integer.parseInt(inputId);
            if (!company.getEmployees().containsKey(employeeId)) {
                System.out.println("Employee not found with ID: " + employeeId);
                return;
            }
            company.getEmployees().remove(employeeId);

            System.out.println("Employee with ID " + employeeId + " has been fired.");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid numeric value for Employee ID.");
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
