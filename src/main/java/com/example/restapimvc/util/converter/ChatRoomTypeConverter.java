package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.ChatRoomType;
import com.example.restapimvc.util.EnumEntityConvertable;
import com.example.restapimvc.util.EnumEntityConverter;

public class ChatRoomTypeConverter extends EnumEntityConverter {
    public ChatRoomTypeConverter() {super(ChatRoomType.class);}

    @Override
    public String convertToDatabaseColumn(EnumEntityConvertable attribute) {
        if(attribute==null){
            attribute = ChatRoomType.NORMAL;
        }
        return super.convertToDatabaseColumn(attribute);
    }
}
