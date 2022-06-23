package com.hanghae99.cloneproject5.repository;

import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoard (Board board);

    void deleteAllByBoardId(Long id);
}
