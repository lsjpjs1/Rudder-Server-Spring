package com.example.restapimvc.notification;

import com.example.restapimvc.pre.chat.SocketMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class SocketMessageWrapper {
    private SocketMessage socketMessage;
}
