package com.example.restapimvc.repository;

import com.example.restapimvc.domain.UserBlock;
import com.example.restapimvc.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {
    Optional<UserBlock> findByUserInfoAndBlockedUserInfo(UserInfo userInfo, UserInfo blockedUserInfo);
}
