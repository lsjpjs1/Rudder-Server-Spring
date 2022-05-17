package com.example.restapimvc.domain;

import com.example.restapimvc.dao.CategoryDao;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
public class UserSelectCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long userSelectCategoryId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    private Long categoryId;

    @Builder
    public UserSelectCategory(UserInfo userInfo, Long categoryId) {
        this.userInfo = userInfo;
        this.categoryId = categoryId;
    }


}
