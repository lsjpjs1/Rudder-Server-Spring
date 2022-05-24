package com.example.restapimvc.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Integer notificationType;
    private Timestamp notificationTime;
    private Long commentId;
    private Long postMessageId;
    private Long userInfoId;
}
