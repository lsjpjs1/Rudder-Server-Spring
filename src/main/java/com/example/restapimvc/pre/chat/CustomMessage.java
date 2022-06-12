package com.example.restapimvc.pre.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomMessage {
    private String sender;
    private String body;
    private String channelId;
}
