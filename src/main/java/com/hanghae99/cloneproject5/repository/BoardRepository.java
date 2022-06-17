package com.hanghae99.cloneproject5.repository;

import com.hanghae99.cloneproject5.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
