package com.example.restapimvc.pre.chat.domain;

import com.example.restapimvc.enums.ChatRoomType;
import com.example.restapimvc.util.converter.ChatRoomTypeConverter;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
@Setter
@ToString
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @Convert(converter = ChatRoomTypeConverter.class)
    private ChatRoomType chatRoomType;

    private Long chatRoomItemId;

}
