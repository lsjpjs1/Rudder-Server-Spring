package com.example.restapimvc.pre.chat.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    private String messageBody;

    private Timestamp messageTime;

    private Long senderUserInfoId;

    private Long chatRoomId;
}
