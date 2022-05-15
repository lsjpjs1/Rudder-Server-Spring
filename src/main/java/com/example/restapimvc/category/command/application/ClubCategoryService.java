package com.example.restapimvc.category.command.application;

import com.example.restapimvc.category.command.domain.Category;
import com.example.restapimvc.category.command.domain.CategoryRepository;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.ClubMember;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubCategoryService {
    private static final String CLUB_CATEGORY_TYPE = "club";
    private final CategoryRepository categoryRepository;
    private final ClubMemberRepository clubMemberRepository;

    @Transactional
    public void joinClub(UserInfo userInfo, CategoryDto.JoinClubRequest joinClubRequest) {
        joinClubRequest.setAllUserInfo(userInfo);
        Category clubCategory = categoryRepository.findById(joinClubRequest.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        if (!clubCategory.getCategoryType().equals(CLUB_CATEGORY_TYPE)) {
            throw new CustomException(ErrorCode.NOT_CLUB_CATEGORY);
        }

        if (!clubCategory.getSchool().getSchoolId().equals(joinClubRequest.getSchoolId())) {
            throw new CustomException(ErrorCode.NOT_SCHOOL_MEMBER);
        }
        if (clubMemberRepository.findTopByCategoryIdAndUserInfoId(clubCategory.getCategoryId(), joinClubRequest.getUserInfoId())
                .isPresent()
        ) {
            throw new CustomException(ErrorCode.ALREADY_CLUB_MEMBER);
        }

        ClubMember clubMember = ClubMember.builder()
                .userInfoId(joinClubRequest.getUserInfoId())
                .categoryId(clubCategory.getCategoryId())
                .build();
        clubMemberRepository.save(clubMember);
    }
}
