package com.example.springdemo.core.response;

import com.example.springdemo.core.entity.Student;
import com.example.springdemo.core.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String fullName;
    private String street;
    private String city;
    private List<SubjectResponse> learningSubjects;
    

    public StudentResponse (Student student){
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullName = student.getFirstName() + " " + student.getLastName();
        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();
        
        if(student.getLearningSubjects() != null){
            learningSubjects = new ArrayList<>();
            for (Subject subject: student.getLearningSubjects())
                learningSubjects.add(new SubjectResponse(subject));
        }
    }
}
