package com.example.restapimvc.comment.command.domain;

import com.example.restapimvc.category.command.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
