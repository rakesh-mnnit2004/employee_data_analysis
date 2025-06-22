package org.rakesh;

import java.io.IOException;
import java.util.List;

import org.rakesh.HierarchyBuilder.Reportee;

public class Main {
    private static final String EXCEL_FILE_PATH = "employees.xlsx";
    private static final String JSON_FILE_PATH = "employee_hierarchy.json";

    public static void main(String[] args) {
        // 1. Generate and write employee data to Excel
        System.out.println("Generating employee data...");
        List<employeeObj> generatedEmployees = EmployeeDataGenerator.generateEmployees(50);
        try {
            EmployeeDataGenerator.writeEmployeesToExcel(generatedEmployees, EXCEL_FILE_PATH);
            System.out.println("Generated " + generatedEmployees.size() + " employees and saved to " + EXCEL_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error writing employees to Excel: " + e.getMessage());
            return;
        }

        // 2. Read employee data from Excel
        System.out.println("\nReading employee data from Excel...");
        List<employeeObj> employees;
        try {
            employees = ReadExcel.readEmployeesFromExcel(EXCEL_FILE_PATH);
            System.out.println("Read " + employees.size() + " employees from " + EXCEL_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error reading employees from Excel: " + e.getMessage());
            return;
        }

        // 3. Identify employees eligible for gratuity
        System.out.println("\nIdentifying employees eligible for gratuity...");
        List<employeeObj> eligibleForGratuity = GratuityCalculator.getEligibleEmployeesForGratuity(employees);
        System.out.println("Employees eligible for gratuity (" + eligibleForGratuity.size() + "): ");
        eligibleForGratuity.forEach(emp -> System.out.println("- " + emp.getName() + " (DOJ: " + emp.getDoj() + ")"));

        // 4. Calculate employees whose salary is greater than their manager's salary
        System.out.println("\nIdentifying employees with higher salary than their manager...");
        List<employeeObj> higherSalaryEmployees = SalaryComparator.getEmployeesWithHigherSalaryThanManager(employees);
        System.out.println("Employees with higher salary than their manager (" + higherSalaryEmployees.size() + "): ");
        higherSalaryEmployees.forEach(emp -> System.out.println("- " + emp.getName() + " (Salary: " + emp.getSalary() + ")"));

        // 5. Build employee hierarchy tree and export to JSON
        System.out.println("\nBuilding employee hierarchy and exporting to JSON...");
        List<Reportee> hierarchy = HierarchyBuilder.buildHierarchy(employees);
        try {
            HierarchyBuilder.writeHierarchyToJson(hierarchy, JSON_FILE_PATH);
            System.out.println("Employee hierarchy exported to " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error exporting hierarchy to JSON: " + e.getMessage());
        }

        // 6. SQL to return the row of an employee whose salary is nth highest
        int n = 3; // Example: 3rd highest salary
        System.out.println("\nSQL query for " + n + "th highest salary:\n" + SQLQueries.getNthHighestSalarySQL(n));
    }
}
