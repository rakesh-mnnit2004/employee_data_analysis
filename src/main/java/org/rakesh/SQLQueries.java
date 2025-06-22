package org.rakesh;

public class SQLQueries {
    public static String getNthHighestSalarySQL(int n) {
        return "SELECT id, name, city, state, category, manager_id, salary, DOJ\n" +
                "FROM (\n" +
                "    SELECT *, DENSE_RANK() OVER (ORDER BY salary DESC) as rnk\n" +
                "    FROM employees\n" +
                ") AS ranked_employees\n" +
                "WHERE rnk = " + n + ";";
    }
}
