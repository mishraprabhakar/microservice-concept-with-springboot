package com.microservice.concepts.notification.rabbitmq;

import com.microservice.concepts.clients.notification.NotificationRequest;
import com.microservice.concepts.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService service;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest request) {
        log.info("Consumer {} from queue", request);
        service.send(request);
    }
}
