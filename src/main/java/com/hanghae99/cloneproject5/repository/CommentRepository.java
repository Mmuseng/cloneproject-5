package com.hanghae99.cloneproject5.repository;

import com.hanghae99.cloneproject5.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
