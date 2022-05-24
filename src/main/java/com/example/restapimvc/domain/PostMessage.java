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
public class PostMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postMessageId;

    private Long sendUserInfoId;
    private Long receiveUserInfoId;
    private Timestamp messageSendTime;
    private String postMessageBody;
    private Boolean isRead;
    private Long postMessageRoomId;
}
