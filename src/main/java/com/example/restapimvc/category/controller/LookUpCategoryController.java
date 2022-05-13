package com.example.restapimvc.category.controller;

import com.example.restapimvc.category.command.application.LookUpCategoryService;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LookUpCategoryController {
    private final LookUpCategoryService lookUpCategoryService;

    @GetMapping(value = "/categories")
    public ResponseEntity<CategoryDto.GetCategoriesResponse> getCategories(@ModelAttribute CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpCategoryService.getCategories(userInfoFromToken, getCategoriesRequest));
    }
}
