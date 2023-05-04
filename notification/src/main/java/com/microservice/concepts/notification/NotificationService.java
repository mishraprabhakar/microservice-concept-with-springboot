package com.microservice.concepts.notification;

import com.microservice.concepts.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository repository;
    public void send(NotificationRequest request) {

        repository.save(
                Notification.builder()
                .message(request.getMessage())
                .sender("Prabhakar Mishra")
                .sendAt(LocalDateTime.now())
                .toCustomerEmail(request.getToCustomerEmail())
                .toCustomerId(request.getToCustomerId())
                .build()
        );

        log.info("Notification is saved successfully into db");

    }
}
