package com.example.springdemo.core.controller;

import com.example.springdemo.core.response.AddressResponse;
import com.example.springdemo.core.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable Long id){

        logger.info("Inside info");
        logger.warn("Inside warning");
        logger.error("Inside error");
        logger.debug("Inside debug");
        logger.trace("Inside trace");

        return new AddressResponse(addressService.getOne(id).stream().findFirst().get());
    }
}
