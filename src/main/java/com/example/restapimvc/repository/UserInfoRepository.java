package com.example.restapimvc.repository;

import com.example.restapimvc.domain.Category;
import com.example.restapimvc.domain.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
    Optional<UserInfo> findUserInfoByUserId(String userId);

    Optional<UserInfo> findUserInfoByUserInfoId(Long userInfoId);

    Optional<UserInfo> findUserInfoByUserNickname(String userNickname);
}

