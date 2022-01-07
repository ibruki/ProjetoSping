package com.example.springdemo.core.controller;

import com.example.springdemo.core.request.CreateSubjectRequest;
import com.example.springdemo.core.response.SubjectResponse;
import com.example.springdemo.core.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    SubjectService subjectService;

    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @PostMapping
    public SubjectResponse create(@RequestBody @Valid CreateSubjectRequest createSubjectRequest){
        return new SubjectResponse(subjectService.create(createSubjectRequest));
    }
}
