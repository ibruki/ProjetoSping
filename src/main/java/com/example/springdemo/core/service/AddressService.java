package com.example.springdemo.core.service;

import com.example.springdemo.core.entity.Address;
import com.example.springdemo.core.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public Optional<Address> getOne(Long id){
        return addressRepository.findById(id);
    }
}
