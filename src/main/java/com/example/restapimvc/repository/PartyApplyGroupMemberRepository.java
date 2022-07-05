package com.example.restapimvc.repository;

import com.example.restapimvc.domain.EmailVerification;
import com.example.restapimvc.pre.party.command.domain.PartyApplyGroup;
import com.example.restapimvc.pre.party.command.domain.PartyApplyGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyApplyGroupMemberRepository extends JpaRepository<PartyApplyGroupMember,Long> {
    List<PartyApplyGroupMember> findByByPartyApplyGroup(PartyApplyGroup partyApplyGroup);
}
