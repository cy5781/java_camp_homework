package com.example;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Klass {

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
