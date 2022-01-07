package com.example.springdemo.core.service;

import com.example.springdemo.core.entity.Subject;
import com.example.springdemo.core.repository.SubjectRepository;
import com.example.springdemo.core.request.CreateSubjectRequest;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public Subject create(CreateSubjectRequest createSubjectRequest){
        return subjectRepository.save(new Subject(createSubjectRequest));
    }
}
