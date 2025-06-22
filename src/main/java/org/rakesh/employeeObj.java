package org.rakesh;

import java.util.Date;

public class employeeObj {
    int id;

    String name;
    String city;
    String state;
    String category;
    Integer manager_id;
    double salary;
    Date DOJ= new Date();

    public employeeObj(int id, String name, String city, String state, String category,  Integer manager_id, double salary, Date DOJ){
        this.id=id;
        this.name=name;
        this.city=city;
        this.state=state;
        this.category=category;
        this.manager_id=manager_id;
        this.salary=salary;
        this.DOJ=DOJ;
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public Integer getManagerId() {
        return manager_id;
    }

    public double getSalary() {
        return salary;
    }

    public Date getDoj() {
        return DOJ;
    }

    // Setters (optional, depending on immutability needs)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setManagerId(Integer managerId) {
        this.manager_id = managerId;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDoj(Date doj) {
        this.DOJ = doj;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", category='" + category + '\'' +
                ", managerId=" + manager_id +
                ", salary=" + salary +
                ", doj=" + DOJ +
                '}';
    }
}
