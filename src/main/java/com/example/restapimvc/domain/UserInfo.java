package com.example.restapimvc.domain;

import com.example.restapimvc.enums.UserInfoOsType;
import com.example.restapimvc.security.Sha1PasswordEncoder;
import com.example.restapimvc.serializer.SchoolSerializer;
import com.example.restapimvc.util.RandomNumber;
import com.example.restapimvc.util.converter.UserInfoOsConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;

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

    public void passwordEncoding() {
        userPassword = Sha1PasswordEncoder.getInstance().encode(userPassword);
    }

    public String passwordReset() {
        String newPassword = RandomNumber.generatePassword();
        userPassword=newPassword;
        passwordEncoding();
        return newPassword;
    }

    public void verifyEmail() {
        this.userType = 0;
    }






}
