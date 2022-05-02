package com.example.restapimvc.common;

import com.example.restapimvc.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;

public class WithUserInfo {
    @Getter
    @Setter
    public static abstract class AbstractWithUserInfo {
        private Long userInfoId;
        private String userId;
        private Long schoolId;

        public void setAllUserInfo(UserInfo userInfo) {
            userInfoId = userInfo.getUserInfoId();
            userId = userInfo.getUserId();
            schoolId = userInfo.getSchool().getSchoolId();
        }
    }
}
