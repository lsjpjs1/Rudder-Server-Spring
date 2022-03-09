package com.example.restapimvc.util.mapper;

import com.example.restapimvc.domain.UserRequest;
import com.example.restapimvc.dto.UserRequestDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserRequestMapper {
    UserRequestDTO.UserRequestResponse entityToUserRequestResponse(UserRequest userRequest);
}