package com.example.restapimvc.repository;

import com.example.restapimvc.domain.UserRequest;
import com.example.restapimvc.domain.UserSelectCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSelectCategoryRepository extends JpaRepository<UserSelectCategory, Long> {
}
