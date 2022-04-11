package com.example.restapimvc.post.query.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostViewRepository extends JpaRepository<PostView, Long> {
    Optional<PostView> findDistinctByPostIdAndIsDelete(Long postId, Boolean isDelete);
}



