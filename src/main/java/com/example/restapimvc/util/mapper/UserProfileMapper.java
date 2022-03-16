package com.example.restapimvc.util.mapper;

import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.dto.UserProfileDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserProfileMapper {
    UserProfileDto.UserProfileResponse entityToUserProfileResponse(UserProfile userProfile);
}
