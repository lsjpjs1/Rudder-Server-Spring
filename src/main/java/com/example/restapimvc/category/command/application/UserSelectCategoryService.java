package com.example.restapimvc.category.command.application;

import com.example.restapimvc.category.command.domain.Category;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.ClubMember;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserSelectCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSelectCategoryService {

    private final UserInfoRepository userInfoRepository;
    @Transactional
    public void updateUserSelectCategory(UserInfo userInfo, CategoryDto.UserSelectCategoryRequest userSelectCategoryRequest) {
        userSelectCategoryRequest.setAllUserInfo(userInfo);
        if (userSelectCategoryRequest.getCategories()==null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }

        UserInfo user = userInfoRepository.findUserInfoByUserInfoId(userSelectCategoryRequest.getUserInfoId()).get();
        user.updateSelectCategories(userSelectCategoryRequest.getCategories());

        userInfoRepository.save(user);
    }
}
