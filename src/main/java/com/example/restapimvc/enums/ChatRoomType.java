package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;
import lombok.Getter;

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
}
