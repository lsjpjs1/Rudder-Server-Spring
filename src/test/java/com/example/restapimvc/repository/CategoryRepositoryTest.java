package com.example.restapimvc.repository;

import com.example.restapimvc.domain.Category;
import com.example.restapimvc.domain.School;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository repository;


    @Test
    public void findAll(){
//        School school = School.builder().schoolId(0).schoolName("hoonUniv").regex("").build();
//        Category category1 = Category.builder().categoryName("test").categoryType("test").categoryOrder(0).categoryEnable(true).school(school).build();
//        repository.save(category1);
//        List<Category> all = repository.findAll();
//        Assertions.assertThat(Arrays.asList(category1)).isEqualTo(Arrays.asList(category1));

    }

}
