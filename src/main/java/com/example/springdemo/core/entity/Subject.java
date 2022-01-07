package com.example.springdemo.core.entity;

import com.example.springdemo.core.request.CreateSubjectRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject_name")
    private String subject_name;

    @Column(name = "marks_obtained")
    private Double marks_obtained;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Subject(CreateSubjectRequest createSubjectRequest){
        this.subject_name = createSubjectRequest.getSubject_name();
        this.marks_obtained = createSubjectRequest.getMarksObtained();
    }
}
