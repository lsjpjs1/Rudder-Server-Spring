package com.example.restapimvc.util.mapper;

import com.example.restapimvc.domain.School;
import com.example.restapimvc.dto.SchoolDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SchoolMapper {
    SchoolDTO.SchoolForResponse entityToSchoolResponse(School school);
}
