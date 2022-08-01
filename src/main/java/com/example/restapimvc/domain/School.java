package com.example.restapimvc.domain;

import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "university")
@Table
@ToString
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    private String schoolName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String regex;

    private String schoolThumbnailName;



    @Builder
    public School(long schoolId, String schoolName, String regex){
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.regex = regex;
    }

    public Boolean validateEmailRegex(String email) {
        if(email.matches(regex)) {
            return true;
        }else{
            return false;
        }
    }
}
