package com.example.restapimvc.util.mapper;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInfoDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfoDto.UserInfoResponse entityToUserInfoResponse(UserInfo userInfo);

    UserInfoDto.UserInfoWithProfileResponse entityToUserInfoWithUserProfileResponse(UserInfo userInfo);

}
