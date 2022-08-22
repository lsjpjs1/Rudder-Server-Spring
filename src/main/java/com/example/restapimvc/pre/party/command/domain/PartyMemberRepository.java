package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {
    List<PartyMember> findByParty(Party party);
    Optional<PartyMember> findTopByPartyAndAndUserInfo(Party party, UserInfo userInfo);
}
