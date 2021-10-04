package com.example.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateStudentRequest {

    @NotNull(message = "ID do estudante necessária!!")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
