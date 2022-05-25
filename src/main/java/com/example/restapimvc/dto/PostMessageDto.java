package com.example.restapimvc.dto;

import com.example.restapimvc.common.WithUserInfo;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class PostMessageDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    @ToString
    public static class SendPostMessageRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long receiveUserInfoId;
        private String messageBody;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    @ToString
    public static class PostMessageRoomsResponse {
        private Long postMessageRoomId;
        private Timestamp messageSendTime;
        private String postMessageBody;
        private String userNickname;
        private String userProfileImageUrl;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    @ToString
    public static class PostMessageResponse {
        private Long postMessageId;
        private Long sendUserInfoId;
        private Long receiveUserInfoId;
        private Timestamp messageSendTime;
        private String postMessageBody;
        private Boolean isRead;
        private Boolean isSender;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    @ToString
    public static class GetPostMessageRoomsResponse {
        private List<PostMessageRoomsResponse> postMessageRooms;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    @ToString
    public static class GetPostMessagesResponse {
        private List<PostMessageResponse> postMessages;

    }


}
