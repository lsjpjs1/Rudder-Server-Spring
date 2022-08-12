package com.example.restapimvc.pre.chat;

import com.example.restapimvc.pre.SocketPayload;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SocketMessage {
    private String messageType;
    private SocketPayload payload;

    public static SocketMessage from(SocketPayload socketPayload) {
        return SocketMessage.builder()
                .messageType(socketPayload.getMessageType())
                .payload(socketPayload)
                .build();
    }
}
