package com.example.restapimvc.repository;

import com.example.restapimvc.domain.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember,Long> {

    Optional<ClubMember> findTopByCategoryIdAndUserInfoId(Long categoryId, Long userInfoId);
}
