package com.example.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Data
public class CreateStudentRequest {

    @NotBlank(message = "Nome não pode ser nulo!")
    private String firstName;

    @NotBlank(message =  "Sobrenome não pode ser nulo!")
    private String lastName;

    private String email;

    private String street;

    private String city;
}
