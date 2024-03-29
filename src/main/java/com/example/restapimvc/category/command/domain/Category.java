package com.example.restapimvc.category.command.domain;

import com.example.restapimvc.domain.School;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    @ManyToOne(targetEntity = School.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    private String categoryType;

    private Integer categoryOrder;

    private Boolean categoryEnable;

    private String categoryAbbreviation;

}
