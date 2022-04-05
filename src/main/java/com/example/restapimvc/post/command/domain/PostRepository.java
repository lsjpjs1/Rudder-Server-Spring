package com.example.restapimvc.post.command.domain;

import com.example.restapimvc.post.query.application.PostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
