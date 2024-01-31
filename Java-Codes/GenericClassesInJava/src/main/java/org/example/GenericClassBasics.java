package org.example;


import java.util.List;

class Student<Type>{

    public Type roll_no;

    public Type exam_roll_no;

    public Student(Type roll_no, Type exam_roll_no, String name, Long mobile_no) {
        this.roll_no = roll_no;
        this.exam_roll_no = exam_roll_no;
        this.name = name;
        this.mobile_no = mobile_no;
    }

//    public Student(){
//
//    }
    public Type getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(Type roll_no) {
        this.roll_no = roll_no;
    }

    public Type getExam_roll_no() {
        return exam_roll_no;
    }

    public void setExam_roll_no(Type exam_roll_no) {
        this.exam_roll_no = exam_roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(Long mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String name;

    public Long mobile_no;

    @Override
    public String toString() {
        return "{ "+name+" : "+roll_no+" }";
    }
}

public class GenericClassBasics {
    public static void main(String[] args) {

        Student<Integer> ghansham=new Student<>(11903033,56647,"ghansham",8624969401L);
        System.out.println(ghansham);

        Student<String> vishal=new Student<>("111","552","vishal",466485L);
        System.out.println(vishal);
    }

}
