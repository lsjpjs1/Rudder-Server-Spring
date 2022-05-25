package com.example.restapimvc.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@AllArgsConstructor
@Builder
public class PostMessageRoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postMessageRoomMemberId;

    private Long postMessageRoomId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
}
