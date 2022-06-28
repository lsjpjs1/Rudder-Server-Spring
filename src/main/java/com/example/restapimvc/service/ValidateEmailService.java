package com.example.restapimvc.service;

import com.example.restapimvc.domain.*;
import com.example.restapimvc.dto.PostMessageDto;
import com.example.restapimvc.dto.SchoolDTO;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.SchoolRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.util.mapper.SchoolMapper;
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
    private final SchoolMapper schoolMapper;

    @Transactional
    public SchoolDTO.SchoolForResponse emailValidate(String userEmail) {
        School school = isEmailInSchools(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.WRONG_EMAIL_FORM));
        userInfoRepository.findUserInfoByUserEmail(userEmail)
                .ifPresent(userInfo -> {throw new CustomException(ErrorCode.EMAIL_ALREADY_EXIST);});
        return schoolMapper.entityToSchoolResponse(school);
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
