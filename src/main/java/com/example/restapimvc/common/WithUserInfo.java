package com.example.restapimvc.common;

import com.example.restapimvc.domain.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

public class WithUserInfo {
    @Getter
    @Setter
    public static abstract class AbstractWithUserInfo {
        @ApiModelProperty(hidden = true)
        private Long userInfoId;
        @ApiModelProperty(hidden = true)
        private String userId;
        @ApiModelProperty(hidden = true)
        private Long schoolId;

        public void setAllUserInfo(UserInfo userInfo) {
            userInfoId = userInfo.getUserInfoId();
            userId = userInfo.getUserId();
            schoolId = userInfo.getSchool().getSchoolId();
        }
    }
}
