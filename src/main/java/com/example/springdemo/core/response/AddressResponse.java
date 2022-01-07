package com.example.springdemo.core.response;

import com.example.springdemo.core.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;

    public AddressResponse(Address address){
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.firstName = address.getStudent().getFirstName();
        this.lastName = address.getStudent().getLastName();
        this.fullName = this.firstName + " " + this.lastName;
        this.email = address.getStudent().getEmail();
    }
}
