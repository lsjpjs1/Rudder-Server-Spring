package com.example.restapimvc.service;

import com.example.restapimvc.domain.*;
import com.example.restapimvc.dto.PostMessageDto;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.SchoolRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidateEmailService {

    private final UserInfoRepository userInfoRepository;
    private final SchoolRepository schoolRepository;

    @Transactional
    public void emailValidate(String userEmail) {
        isEmailInSchools(userEmail)
                .orElseThrow(()-> new CustomException(ErrorCode.WRONG_EMAIL_FORM));
        userInfoRepository.findUserInfoByUserEmail(userEmail)
                .ifPresent(userInfo -> {throw new CustomException(ErrorCode.EMAIL_ALREADY_EXIST);});
    }

    private Optional<School> isEmailInSchools(String email) {
        List<School> schools = schoolRepository.findAll();
        School targetSchool = null;
        for(School school : schools){
            if(school.validateEmailRegex(email)){
                targetSchool = school;
                break;
            }
        }
        return Optional.ofNullable(targetSchool);
    }
}
