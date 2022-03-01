package com.example.restapimvc.service;

import com.example.restapimvc.domain.Category;
import com.example.restapimvc.dto.CategoryDTO;
import com.example.restapimvc.dto.TokenDto;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.repository.CategoryQueryRepository;
import com.example.restapimvc.repository.CategoryRepository;
import com.example.restapimvc.repository.SchoolRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class AuthServiceTest {
    private AuthService authService;

    private CategoryRepository categoryRepository;

    private SchoolRepository schoolRepository;

    private UserInfoRepository userInfoRepository;

    private CategoryQueryRepository categoryQueryRepository;

    public enum TestEnum{
        TEST
    }

    @Autowired
    public AuthServiceTest(AuthService authService,
                           CategoryRepository categoryRepository,
                           SchoolRepository schoolRepository,
                           UserInfoRepository userInfoRepository,
                           CategoryQueryRepository categoryCustomRepository){
        this.authService = authService;
        this.categoryRepository = categoryRepository;
        this.schoolRepository = schoolRepository;
        this.userInfoRepository = userInfoRepository;
        this.categoryQueryRepository = categoryCustomRepository;
    }

    @Test
    public void allTest(){
        List<CategoryDTO.CommonCategoryResponse> categories = categoryQueryRepository.findCommonCategory(99l,1l);
        categories.forEach(
                (cat)->{
                    System.out.println(cat.toString());
                });
    }

    @Test
    public void loginTest(){
        System.out.println();
        UserInfoDto.LoginRequest loginRequest = new UserInfoDto.LoginRequest("fff","123123123a","token","android");
        TokenDto tokenDto = authService.login(loginRequest);
        System.out.println(tokenDto.getAccessToken());
    }


}
