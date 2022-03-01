package com.example.restapimvc.controller;

import com.example.restapimvc.dto.CategoryDTO;
import com.example.restapimvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO.CommonCategoryResponse>> getCommonCategories() {
        List<CategoryDTO.CommonCategoryResponse> commonCategories = categoryService.getCommonCategories();
        return ResponseEntity.ok(commonCategories);
    }


}
