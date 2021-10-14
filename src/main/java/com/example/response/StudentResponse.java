package com.example.response;

import com.example.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

    private String firstName;

    private String lastName;

    private String email;

    public String fullName;

    public StudentResponse (Student student){
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullName = student.getFirstName() + " " + student.getLastName();
    }
}
