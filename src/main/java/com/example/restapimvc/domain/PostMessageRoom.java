package com.example.restapimvc.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@AllArgsConstructor
@Builder
public class PostMessageRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postMessageRoomId;


}
