package com.example.restapimvc.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@AllArgsConstructor
@Builder
public class PostMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postMessageId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "send_user_info_id")
    private UserInfo sendUserInfo;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_user_info_id")
    private UserInfo receiveUserInfo;

    @Column(insertable = false)
    private Timestamp messageSendTime;

    private String postMessageBody;

    @Column(insertable = false)
    private Boolean isRead;

    private Long postMessageRoomId;
}
