package com.example.restapimvc.pre.chat;

import com.example.restapimvc.common.WithUserInfo;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class ChatDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class ChatRoomDto {
        private Long chatRoomId;
        private String recentMessage;
        private Timestamp recentMessageTime;
        private Integer notReadMessageCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetChatRoomsResponse {
        private List<ChatRoomDto> chatRooms;
    }
}
