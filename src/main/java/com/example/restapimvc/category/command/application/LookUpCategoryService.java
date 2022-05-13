package com.example.restapimvc.category.command.application;

import com.example.restapimvc.category.command.domain.CategoryQueryRepository;
import com.example.restapimvc.category.command.domain.CategoryRepository;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookUpCategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryQueryRepository categoryQueryRepository;
    @Transactional
    public CategoryDto.GetCategoriesResponse getCategories(UserInfo userInfo, CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        getCategoriesRequest.setAllUserInfo(userInfo);
        List<CategoryDto.CategoryResponse> categories = categoryQueryRepository.findCategories(getCategoriesRequest);
        return CategoryDto.GetCategoriesResponse.builder().categories(categories).build();

    }
}
