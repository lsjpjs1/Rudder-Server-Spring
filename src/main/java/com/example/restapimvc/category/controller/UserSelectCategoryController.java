package com.example.restapimvc.category.controller;

import com.example.restapimvc.category.command.application.UserSelectCategoryService;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.repository.UserSelectCategoryRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserSelectCategoryController {

    private final UserSelectCategoryService userSelectCategoryService;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/categories/user")
    public ResponseEntity updateUserSelectCategory(@RequestBody CategoryDto.UserSelectCategoryRequest userSelectCategoryRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        userSelectCategoryService.updateUserSelectCategory(userInfoFromToken, userSelectCategoryRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }
}
