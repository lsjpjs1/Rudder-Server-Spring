package com.example.restapimvc.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
public class CustomerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerRequestId;

    private String customerRequestType;

    private String customerRequestBody;

    private Long userInfoId;

    private Long itemId;
}
