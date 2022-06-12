package com.example.restapimvc.pre.party.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {

}
