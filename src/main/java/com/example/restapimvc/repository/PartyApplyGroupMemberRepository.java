package com.example.restapimvc.repository;

import com.example.restapimvc.domain.EmailVerification;
import com.example.restapimvc.pre.party.command.domain.PartyApplyGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyApplyGroupMemberRepository extends JpaRepository<PartyApplyGroupMember,Long> {
}
