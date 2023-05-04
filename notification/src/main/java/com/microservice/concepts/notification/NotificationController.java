package com.microservice.concepts.notification;

import com.microservice.concepts.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest request){

        log.info("Notification is saved into db for {}", request);
        service.send(request);

    }
}
