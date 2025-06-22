package org.rakesh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryComparator {

    public static List<employeeObj> getEmployeesWithHigherSalaryThanManager(List<employeeObj> employees) {
        List<employeeObj> result = new ArrayList<>();
        Map<Integer, employeeObj> employeeMap = new HashMap<>();

        // Populate employee map for quick lookup by ID
        for (employeeObj employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }

        for (employeeObj employee : employees) {
            if (employee.getManagerId() != null) {
                employeeObj manager = employeeMap.get(employee.getManagerId());
                if (manager != null && employee.getSalary() > manager.getSalary()) {
                    result.add(employee);
                }
            }
        }
        return result;
    }
}

