package com.example.restapimvc.pre.party.command.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class PickUpPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickUpPlaceId;

    private String pickUpPlaceName;
}
