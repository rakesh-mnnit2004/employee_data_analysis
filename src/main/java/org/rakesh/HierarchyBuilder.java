package org.rakesh;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

public class HierarchyBuilder {

    public static class Reportee {

        public int id;

        public String name;

        public String role;

        public List<Reportee> reportees;

        public Reportee(int id, String name, String role) {

            this.id = id;

            this.name = name;

            this.role = role;

            this.reportees = new ArrayList<>();

        }

    }

    public static List<Reportee> buildHierarchy(List<employeeObj> employees) {

        Map<Integer, Reportee> reporteeMap = new HashMap<>();

        List<Reportee> topLevelReportees = new ArrayList<>();

        // Create all reportee nodes and populate map

        for (employeeObj employee : employees) {

            reporteeMap.put(employee.getId(), new Reportee(employee.getId(), employee.getName(), employee.getCategory()));

        }

        // Build hierarchy

        for (employeeObj employee : employees) {

            Reportee currentReportee = reporteeMap.get(employee.getId());

            if (employee.getManagerId() != null) {

                Reportee manager = reporteeMap.get(employee.getManagerId());

                if (manager != null) {

                    manager.reportees.add(currentReportee);

                } else {

                    // If manager not found, treat as top-level (e.g., manager not in the current list)

                    topLevelReportees.add(currentReportee);

                }

            } else {

                // This is a top-level employee (no manager)

                topLevelReportees.add(currentReportee);

            }

        }

        return topLevelReportees;

    }

    public static void writeHierarchyToJson(List<Reportee> hierarchy, String filePath) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.writeValue(new File(filePath), hierarchy);

    }

}

