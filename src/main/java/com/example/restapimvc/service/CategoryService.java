package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.CategoryDTO;
import com.example.restapimvc.dto.UserTokenInfoDTO;
import com.example.restapimvc.repository.CategoryQueryRepository;
import com.example.restapimvc.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    public List<CategoryDTO.CommonCategoryResponse> getCommonCategories() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = (UserInfo) principal;
        return categoryQueryRepository.findCommonCategory(userInfo.getUserInfoId(), userInfo.getSchool());
    }
}
