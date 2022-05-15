package com.example.restapimvc.category.controller;

import com.example.restapimvc.category.command.application.ClubCategoryService;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClubCategoryController {
    private final ClubCategoryService clubCategoryService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/categories/{categoryId}/join")
    public ResponseEntity joinClub(@PathVariable Long categoryId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        clubCategoryService.joinClub(userInfoFromToken, CategoryDto.JoinClubRequest.builder().categoryId(categoryId).build());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }
}
