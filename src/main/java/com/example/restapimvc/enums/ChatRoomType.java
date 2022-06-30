package com.example.restapimvc.enums;

import com.example.restapimvc.pre.chat.domain.ChatRoom;
import com.example.restapimvc.util.EnumEntityConvertable;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ChatRoomType implements EnumEntityConvertable {

    NORMAL("normal");

    private String chatRoomType;

    ChatRoomType(String chatRoomType) {this.chatRoomType = chatRoomType;}

    @Override
    public String getEntityValue() {
        return chatRoomType;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return chatRoomType.equals(s);
    }

    @JsonCreator
    public static ChatRoomType from(String s){
        return Arrays.stream(values())
                .filter(v -> v.chatRoomType.equalsIgnoreCase(s))
                .findFirst()
                .orElse(null);
    }
}
