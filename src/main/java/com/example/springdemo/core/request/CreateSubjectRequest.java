package com.example.springdemo.core.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class CreateSubjectRequest {

    @NotBlank(message = "Precisa ter nome!!")
    private String subject_name;

    private Double marksObtained;
}
