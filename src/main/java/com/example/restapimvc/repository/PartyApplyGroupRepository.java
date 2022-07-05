package com.example.restapimvc.repository;

import com.example.restapimvc.domain.EmailVerification;
import com.example.restapimvc.pre.party.command.domain.PartyApplyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyApplyGroupRepository extends JpaRepository<PartyApplyGroup,Long> {
}
