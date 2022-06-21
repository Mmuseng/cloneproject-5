package com.hanghae99.cloneproject5.repository;

import com.hanghae99.cloneproject5.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByAccessTokenAndRefreshToken(String accessToken, String refreshToken);
}
