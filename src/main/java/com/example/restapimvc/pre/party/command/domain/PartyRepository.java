package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.post.command.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
}
