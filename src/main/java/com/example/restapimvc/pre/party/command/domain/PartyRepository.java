package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.post.command.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    @Query(nativeQuery = true,value = "select * from party where party.party_host_user_info_id = (:userInfoId) and date(party.party_time) = date(:partyTime)")
    Optional<Party> findByPartyHostUserInfoIdAndAndPartyTime(@Param("userInfoId") Long userInfoId, @Param("partyTime") Timestamp partyTime);
}
