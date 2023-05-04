package com.microservice.concepts.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckService service;
    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerID){

        boolean isFraudulent = service
                .isFraudulent(customerID);

        return new FraudCheckResponse(isFraudulent);
    }
}
