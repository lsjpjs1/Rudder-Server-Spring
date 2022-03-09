package com.example.restapimvc.util.mapper;

import com.example.restapimvc.domain.UserBlock;
import com.example.restapimvc.dto.UserBlockDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserBlockMapper {

    UserBlockDTO.CreateBlockUserResponse entityToCreateBlockUserResponse(UserBlock userBlock);
}
