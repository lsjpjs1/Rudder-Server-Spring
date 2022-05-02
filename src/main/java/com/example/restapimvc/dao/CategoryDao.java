package com.example.restapimvc.dao;

import com.example.restapimvc.domain.School;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "category")
@Table
@ToString
public class CategoryDao {

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
    public CategoryDao(String categoryName, School school, String categoryType, int categoryOrder, boolean categoryEnable){
        this.categoryName = categoryName;
        this.school = school;
        this.categoryType = categoryType;
        this.categoryOrder = categoryOrder;
        this.categoryEnable = categoryEnable;
    }

}
