package com.example.restapimvc.pre.chat;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class CustomMessage {
    private String sender;
    private String body;
    private Long channelId;
    private Timestamp sendTime;


}
