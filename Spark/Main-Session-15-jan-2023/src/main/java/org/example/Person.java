package org.example;

import java.io.Serializable;

public class Person implements Serializable {
    public Integer age;
    public String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer salary;
    public String designation;

    public boolean isAdult(){
        return age>=18;
    }
    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", designation='" + designation + '\'' +
                '}';
    }

    public Person(Integer age, String name, Integer salary, String designation) {
        this.age = age;
        this.name = name;
        this.salary = salary;
        this.designation = designation;
    }
}
