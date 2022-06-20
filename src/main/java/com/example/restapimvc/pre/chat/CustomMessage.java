package com.example.restapimvc.pre.chat;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CustomMessage {
    private String sender;
    private String body;
    private Long channelId;
    private Timestamp sendTime;
}
