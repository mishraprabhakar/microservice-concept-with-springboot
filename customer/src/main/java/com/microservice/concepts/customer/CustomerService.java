package com.microservice.concepts.customer;

import com.microservice.concepts.clients.fraud.FraudCheckResponse;
import com.microservice.concepts.clients.fraud.FraudClient;
import com.microservice.concepts.clients.notification.NotificationClient;
import com.microservice.concepts.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository repository,
                              RestTemplate template,
                              FraudClient client,
                              NotificationClient notificationClient) {


    public void register(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo : check if email is valid
        //todo : check if email is already taken
        repository.saveAndFlush(customer);
        //todo : is fraudster

        FraudCheckResponse fraudCheckResponse = client.isFraudster(customer.getId());

        /*FraudCheckResponse fraudCheckResponse = template.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );*/

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }

        //todo : make it async. i.e add to queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        String.format("Hi %s, welcome to Microservice Concept Tutorial..."
                                .formatted(customer.getFirstName())),
                        customer.getEmail(),
                        customer.getId())
                );

    }
}
