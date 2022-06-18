package com.hanghae99.cloneproject5.repository;

import com.hanghae99.cloneproject5.model.Board;
import com.hanghae99.cloneproject5.model.Like;
import com.hanghae99.cloneproject5.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByBoard(Board board);

    Optional<Like> findByBoardAndMember(Board board, Member member);
}
