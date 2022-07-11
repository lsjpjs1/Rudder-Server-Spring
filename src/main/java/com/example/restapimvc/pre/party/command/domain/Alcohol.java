package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.enums.AlcoholUnit;
import com.example.restapimvc.enums.Currency;
import com.example.restapimvc.util.converter.AlcoholUnitConverter;
import com.example.restapimvc.util.converter.ChatRoomTypeConverter;
import com.example.restapimvc.util.converter.CurrencyConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alcoholId;

    private String alcoholName;

    @Setter
    @JsonProperty("alcoholImageUrl")
    private String alcoholImageName;

    private BigDecimal price;

    private BigDecimal originalPrice;

    @Convert(converter = CurrencyConverter.class)
    private Currency currency;

    private Integer alcoholCount;

    @Convert(converter = AlcoholUnitConverter.class)
    private AlcoholUnit alcoholUnit;

}
