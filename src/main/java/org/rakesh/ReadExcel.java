package org.rakesh;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.rakesh.employeeObj;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ReadExcel {

    public static List<employeeObj> readEmployeesFromExcel(String filePath) throws IOException {
        List<employeeObj> employees = new ArrayList<>();

        String path = "/home/rakesh/project/java/swissre/src/main/java/data/doctordetails.xlsx";

        File file = new File(filePath);
        FileInputStream file1 = null;
        Workbook workbook = null;
        try {
            file1 = new FileInputStream(file);
            try {

                workbook = new XSSFWorkbook(file1);
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) { // Skip header row
                        continue;
                    }

                    int id = (int) row.getCell(0).getNumericCellValue();
                    String name = row.getCell(1).getStringCellValue();
                    String city = row.getCell(2).getStringCellValue();
                    String state = row.getCell(3).getStringCellValue();
                    String category = row.getCell(4).getStringCellValue();
                  //  Integer managerId = (Integer) row.getCell(5).getNumericCellValue();

                    Integer managerId = null;
                    Cell managerIdCell = row.getCell(5);
                    if (managerIdCell != null && managerIdCell.getCellType() == CellType.NUMERIC) {
                        managerId = (int) managerIdCell.getNumericCellValue();
                    }


                    double salary = row.getCell(6).getNumericCellValue();
                    Date doj = row.getCell(7).getDateCellValue();
                    System.out.print(id + " " + name + city + state + category + managerId + salary + doj);
                    System.out.println();
                    employees.add(new employeeObj(id, name, city, state, category, managerId, salary, doj));
                }


            } catch (IOException e) {
                System.out.println("IO exception");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        workbook.close();
        file1.close();

        return employees;


    }
}