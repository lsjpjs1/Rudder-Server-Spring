package com.example.restapimvc.repository;

import com.example.restapimvc.domain.AddCategoryRequest;
import com.example.restapimvc.domain.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddCategoryRequestRepository extends JpaRepository<AddCategoryRequest,Long> {
}
