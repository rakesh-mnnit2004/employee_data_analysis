

package org.rakesh;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Calendar;

public class EmployeeDataGenerator {

    private static final String[] NAMES = {"Ravi", "Shivam", "Rama", "Krishna", "Sreekanth", "Manoj", "Priya", "Amit", "Sneha", "Rahul", "Deepak", "Pooja", "Vikram", "Anjali", "Suresh", "Geeta", "Rajesh", "Meena", "Alok", "Nisha"};
    private static final String[] CITIES = {"Hyderabad", "Bangalore", "Chennai", "Mumbai", "Delhi", "Kolkata", "Pune", "Ahmedabad", "Jaipur", "Lucknow"};
    private static final String[] STATES = {"Telangana", "Karnataka", "Tamilnadu", "Maharashtra", "Delhi", "West Bengal", "Maharashtra", "Gujarat", "Rajasthan", "Uttar Pradesh"};
   private static final String[] CATEGORIES = {"employee", "manager", "Director"};
    private static final Random RANDOM = new Random();

    public static List<employeeObj> generateEmployees(int count) {
        List<employeeObj> employees = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            int id = 1000 + i;
            String name = NAMES[RANDOM.nextInt(NAMES.length)];
            String city = CITIES[RANDOM.nextInt(CITIES.length)];
            String state = STATES[RANDOM.nextInt(STATES.length)];
            String category = CATEGORIES[RANDOM.nextInt(CATEGORIES.length)];
            Integer manager_id = null;
            if (!category.equals("Director") && !employees.isEmpty()) {
               // Assign a random manager from existing employees who are managers or directors
                List<employeeObj> potentialManagers = new ArrayList<>();
                for(employeeObj emp : employees) {
                    if (emp.getCategory().equals("manager") || emp.getCategory().equals("Director")) {
                        potentialManagers.add(emp);
                    }
                }
                if (!potentialManagers.isEmpty() && manager_id != null) {
                    manager_id = potentialManagers.get(RANDOM.nextInt(potentialManagers.size())).getId();
                } else if (category.equals("employee") && employees.size() > 0) {
                    // If no managers/directors yet, assign any existing employee as manager for employees
                    manager_id = employees.get(RANDOM.nextInt(employees.size())).getId();
                }

            }

            double salary = 30000 + (RANDOM.nextDouble() * 120000);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -RANDOM.nextInt(10)); // Random date within last 10 years
            cal.add(Calendar.DAY_OF_YEAR, -RANDOM.nextInt(365));
            Date doj = cal.getTime();

            employees.add(new employeeObj(id, name, city, state, category, manager_id, salary, doj));
        }
        return employees;
    }

    public static void writeEmployeesToExcel(List<employeeObj> employees, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"id", "name", "city", "state", "category", "manager_id", "salary", "DOJ"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // Create data rows
        int rowNum = 1;
        for (employeeObj employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getCity());
            row.createCell(3).setCellValue(employee.getState());
            row.createCell(4).setCellValue(employee.getCategory());
            if (employee.getManagerId() != null) {
                row.createCell(5).setCellValue(employee.getManagerId());
            } else {
                row.createCell(5).setCellValue("null"); // Empty string for null manager_id
            }
            row.createCell(6).setCellValue(employee.getSalary());

            // Format date
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d-MMM-yyyy"));
            Cell dojCell = row.createCell(7);
            dojCell.setCellValue(employee.getDoj());
            dojCell.setCellStyle(dateCellStyle);
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}

