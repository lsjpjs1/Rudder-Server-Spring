package com.example.restapimvc.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
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

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType='" + categoryType + '\'' +
                ", categoryOrder=" + categoryOrder +
                ", categoryEnable=" + categoryEnable +
                '}';
    }

    @Builder
    public Category(String categoryName, School school, String categoryType, int categoryOrder, boolean categoryEnable){
        this.categoryName = categoryName;
        this.school = school;
        this.categoryType = categoryType;
        this.categoryOrder = categoryOrder;
        this.categoryEnable = categoryEnable;
    }

}
