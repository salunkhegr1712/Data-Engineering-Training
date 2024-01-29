package org.example;

import java.beans.Encoder;
import java.io.Serializable;

public class Employee implements Serializable {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String birthdate;
    private int salary;
    private String department;
    private String joiningDate;
    private boolean isManager;
    private int bonus;
    private String email;

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Constructor, getters, and setters

    public Employee(int employeeID, String firstName, String lastName, String birthdate, int salary, String department,
                    String joiningDate, boolean isManager, int bonus, String email) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.salary = salary;
        this.department = department;
        this.joiningDate = joiningDate;
        this.isManager = isManager;
        this.bonus = bonus;
        this.email = email;
    }

    // Add getters and setters as needed
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", isManager=" + isManager +
                ", bonus=" + bonus +
                ", email='" + email + '\'' +
                '}';
    }
}