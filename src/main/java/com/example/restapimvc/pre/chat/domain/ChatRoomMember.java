package com.example.restapimvc.pre.chat.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class ChatRoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomMemberId;

    private Long userInfoId;
    private Long chatRoomId;

    private Timestamp latestReadTime;

    public void updateLatestReadTime() {
        latestReadTime = new Timestamp(System.currentTimeMillis());
    }
}
