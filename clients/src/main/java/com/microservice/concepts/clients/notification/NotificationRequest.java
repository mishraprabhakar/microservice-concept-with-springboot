package com.microservice.concepts.clients.notification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private String message;
    private String toCustomerEmail;
    private Integer toCustomerId;

}
