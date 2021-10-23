package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CampusService {

    @Autowired
    private School school;
    @Autowired
    private Klass klass;
    @Autowired
    private Student student;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CampusService(School school, Klass klass, Student student) {
        this.school = school;
        this.klass = klass;
        this.student = student;
    }

    public void activity (){
        System.out.println("活动开始 "+"school"+school.toString()+"klass "+school.class1 + "student" +student.toString() );
    }

}
