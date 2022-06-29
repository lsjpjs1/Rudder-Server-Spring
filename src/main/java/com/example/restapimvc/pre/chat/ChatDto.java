package com.example.restapimvc.pre.chat;

import com.example.restapimvc.common.WithUserInfo;
import io.swagger.annotations.ApiModelProperty;
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


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetChatMessagesRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long endChatMessageId;

        @ApiModelProperty(hidden = true)
        private Long chatRoomId;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartyGroupChatRoomRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(hidden = true)
        private Long partyId;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetChatMessagesResponse {
        private List<ChatMessageDto> chatMessages;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class ChatMessageDto {
        private Long chatMessageId;
        private String chatMessageBody;
        private Timestamp chatMessageTime;
        private Long sendUserInfoId;
        private String sendUserNickname;
        private Boolean isMine;
        private Long chatRoomId;
    }


}
