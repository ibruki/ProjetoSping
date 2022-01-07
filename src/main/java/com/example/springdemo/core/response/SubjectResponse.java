package com.example.springdemo.core.response;

import com.example.springdemo.core.entity.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {
    private Long id;
    private String subject_name;
    private Double markObtained;

    public SubjectResponse(Subject subject){
        this.id = subject.getId();
        this.subject_name = subject.getSubject_name();
        this.markObtained = subject.getMarks_obtained();
    }
}
