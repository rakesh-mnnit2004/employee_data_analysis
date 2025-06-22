package org.rakesh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GratuityCalculator {

    private static final int GRATUITY_ELIGIBILITY_MONTHS = 60;

    public static List<employeeObj> getEligibleEmployeesForGratuity(List<employeeObj> employees) {
        List<employeeObj> eligibleEmployees = new ArrayList<>();
        Date currentDate = new Date();

        for (employeeObj employee : employees) {
            long diffInMillies = Math.abs(currentDate.getTime() - employee.getDoj().getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            long diffInMonths = (long) (diffInDays / (365/12.0)); // Approximate months

            if (diffInMonths >= GRATUITY_ELIGIBILITY_MONTHS) {
                eligibleEmployees.add(employee);
            }
        }
        return eligibleEmployees;
    }
}

