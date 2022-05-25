package com.example.restapimvc.repository;

import com.example.restapimvc.domain.PostMessageRoom;
import com.example.restapimvc.domain.PostMessageRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostMessageRoomMemberRepository extends JpaRepository<PostMessageRoomMember,Long> {

    @Query(value = "SELECT pmrm1 from PostMessageRoomMember pmrm1 " +
            "        left join PostMessageRoomMember pmrm2 on " +
            "        pmrm1.postMessageRoomId = pmrm2.postMessageRoomId " +
            "        where (pmrm1.userInfo.userInfoId = ?1 and pmrm2.userInfo.userInfoId = ?2) or (pmrm1.userInfo.userInfoId = ?2 and pmrm2.userInfo.userInfoId = ?1) ")
    List<PostMessageRoomMember> findBySenderAndReceiver(Long sendUserInfoId, Long receiveUserInfoId);
}
