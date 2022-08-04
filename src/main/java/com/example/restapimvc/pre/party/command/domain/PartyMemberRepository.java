package com.example.restapimvc.pre.party.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {
    List<PartyMember> findByParty(Party party);
}
