package com.microservice.concepts.customer;

import com.microservice.concepts.amqp.RabbitMQMessageProducer;
import com.microservice.concepts.clients.fraud.FraudCheckResponse;
import com.microservice.concepts.clients.fraud.FraudClient;
import com.microservice.concepts.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final FraudClient client;
    private final RabbitMQMessageProducer messageProducer;


    public void register(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo : check if email is valid
        //todo : check if email is already taken
        repository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = client.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }

        var notificationRequest = new NotificationRequest(
                String.format("Hi %s, welcome to Microservice Concept Tutorial..."
                        .formatted(customer.getFirstName())),
                customer.getEmail(),
                customer.getId());

        messageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");

    }
}
