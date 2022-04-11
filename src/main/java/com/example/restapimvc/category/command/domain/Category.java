package com.example.restapimvc.category.command.domain;

import com.example.restapimvc.domain.School;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@ToString
public class Category implements Serializable {

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



    @Builder
    public Category(String categoryName, School school, String categoryType, int categoryOrder, boolean categoryEnable){
        this.categoryName = categoryName;
        this.school = school;
        this.categoryType = categoryType;
        this.categoryOrder = categoryOrder;
        this.categoryEnable = categoryEnable;
    }

}
