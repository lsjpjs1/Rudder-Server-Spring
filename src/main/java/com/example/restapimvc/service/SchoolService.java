package com.example.restapimvc.service;

import com.example.restapimvc.domain.School;
import com.example.restapimvc.dto.SchoolDTO;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.SchoolRepository;
import com.example.restapimvc.util.mapper.SchoolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolMapper schoolMapper;

    private final SchoolRepository schoolRepository;
    public SchoolDTO.GetSchoolsResponse getSchools() {
        List<School> schools = schoolRepository.findAll();
        return SchoolDTO.GetSchoolsResponse.builder().schools(toDtoList(schools)).build();
    }

    public List<SchoolDTO.SchoolForResponse> toDtoList(List<School> schools) {
        List<SchoolDTO.SchoolForResponse> schoolResponses = new ArrayList<>();
        for(School school : schools) {
            schoolResponses.add(schoolMapper.entityToSchoolResponse(school));
        }
        return schoolResponses;
    }

    public void validateEmailRegex(String userEmail,UserInfoDto.ValidateEmailRequest validateEmailRequest) {
        School school = schoolRepository.findById(validateEmailRequest.getSchoolId())
                .orElseThrow(() -> new CustomException(ErrorCode.SCHOOL_ID_NOT_FOUND));
        if(!userEmail.matches(school.getRegex())) {
            throw new CustomException(ErrorCode.WRONG_EMAIL_FORM);
        }

    }

}
