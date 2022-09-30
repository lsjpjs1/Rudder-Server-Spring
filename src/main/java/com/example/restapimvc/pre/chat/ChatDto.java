package com.example.restapimvc.pre.chat;

import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.enums.ChatRoomType;
import com.example.restapimvc.notification.SocketPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
        private String chatRoomImageUrl;
        private String chatRoomTitle;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class OneToOneChatRoomDto {
        private Long chatRoomId;
        private String recentMessage;
        private Timestamp recentMessageTime;
        private Integer notReadMessageCount;
        private String chatRoomImageUrl;
        private String chatRoomTitle;
        private Long otherUserInfoId;
        private Long partyId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetChatRoomsResponse {
        private List<OneToOneChatRoomDto> chatRooms;
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
    public static class ChatMessageDto implements SocketPayload {
        private Long chatMessageId;
        private String chatMessageBody;
        private Timestamp chatMessageTime;
        private Long sendUserInfoId;
        private String sendUserNickname;
        private Boolean isMine;
        private Long chatRoomId;

        @JsonIgnore
        @Override
        public String getMessageType() {
            return "CHAT_MESSAGE";
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreateChatRoomRequest extends WithUserInfo.AbstractWithUserInfo {
        private List<Long> userInfoIdList;
        private ChatRoomType chatRoomType;
        private Long chatRoomItemId;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreateChatRoomResponse {
        private Long chatRoomId;

    }


}
