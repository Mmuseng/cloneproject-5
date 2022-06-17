package com.hanghae99.cloneproject5.repository;

import com.hanghae99.cloneproject5.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
