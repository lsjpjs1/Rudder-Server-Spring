package com.example.restapimvc.domain;

import com.example.restapimvc.enums.UserInfoOsType;
import com.example.restapimvc.post.command.domain.PostLike;
import com.example.restapimvc.security.Sha1PasswordEncoder;
import com.example.restapimvc.serializer.SchoolSerializer;
import com.example.restapimvc.util.RandomNumber;
import com.example.restapimvc.util.converter.UserInfoOsConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@ToString
@Builder
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInfoId;

    private String userId;

    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(targetEntity = School.class, fetch = FetchType.LAZY)
    @JsonSerialize(using = SchoolSerializer.class)
    @JoinColumn(name = "school_id")
    private School school;

    @Setter
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;

    @Setter
    private String userNickname;

    @Setter
    private Integer userType;

    @Convert(converter = UserInfoOsConverter.class)
    @Setter
    private UserInfoOsType os;

    @Setter
    private String notificationToken;

    private String userEmail;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserSelectCategory> userSelectCategories;

    public void passwordEncoding() {
        userPassword = Sha1PasswordEncoder.getInstance().encode(userPassword);
    }

    public String passwordReset() {
        String newPassword = RandomNumber.generatePassword();
        userPassword = newPassword;
        passwordEncoding();
        return newPassword;
    }

    public void verifyEmail() {
        this.userType = 0;
    }

    public void updateSelectCategories(List<Long> categories) {
        List<UserSelectCategory> newSelectedCategories = new ArrayList<>();
        for (Long categoryId : categories) {
            newSelectedCategories.add(UserSelectCategory.builder().userInfo(this).categoryId(categoryId).build());
        }
        userSelectCategories.clear();
        userSelectCategories.addAll(newSelectedCategories);
    }


}
