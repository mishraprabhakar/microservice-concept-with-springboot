package com.microservice.concepts.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository repository) {


    public void register(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo : check if email is valid
        //todo : check if email is already taken
        repository.save(customer);
    }
}
