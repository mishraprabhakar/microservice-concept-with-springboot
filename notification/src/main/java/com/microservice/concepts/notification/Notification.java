package com.microservice.concepts.notification;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Notification {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    private Integer notificationId;
    private String message;
    private String sender;
    private LocalDateTime sendAt;
    private String toCustomerEmail;
    private Integer toCustomerId;
}
