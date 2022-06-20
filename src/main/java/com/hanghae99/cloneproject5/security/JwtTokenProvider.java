package com.hanghae99.cloneproject5.security;

import com.hanghae99.cloneproject5.security.memberDetail.MemberDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@Slf4j
public class JwtTokenProvider {

    // JwtTokenProvider 에서 이루어지는 일
    // - 만료시간 설정
    // - 토근의 생성과 권한 설정 및 유효성 검사
    // - SecurityContextHolder 에 저장할 Authentication 객체 생성 및 저장
    // - (임시) secretKey => 별도의 properties 파일 생성하여 보관
    // - SecurityContextHolder 에 저장할 Authentication 객체 생성
    // - 전달받은 jwt 에서 username 추출
    // - refresh token 은 나중에 추가해보자..

    private static final String JWT_SECRET = "secretKey";

    // 토큰 유효시간
    private static final int JWT_EXPIRATION_MS = 604800000;

    // jwt 토큰 생성
    public static String generateToken(Authentication authentication) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject((String) authentication.getPrincipal()) // 사용자
                .setIssuedAt(new Date()) // 현재 시간 기반으로 생성
                .setExpiration(expiryDate) // 만료 시간 세팅
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // 사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                .compact();
    }

    // Jwt 토큰에서 아이디 추출
    public static String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Jwt 토큰 유효성 검사
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }



    // 첫번째 버전
/*    private static final String secretKey = "wlqrkrhtlvek";

    private MemberDetailsServiceImpl memberDetailsService;

    private final Long expireTime = 30 * 60 * 1000L;

    // JWT 생성
    public String createToken(Authentication authentication) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireTime);

        return Jwts.builder()
                // username
                .setSubject( (String) authentication.getPrincipal())
                // 만료시간
                .setExpiration(expireDate)
                // 생성시간
                .setIssuedAt(nowDate)
                // 암호화 알고리즘 / secret key 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Jwt에서 username 추출
    public String getUsernameFromJwt(String Jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJwt(Jwt)
                .getBody();
        return claims.getSubject();
    }

    // 인증 성공시 SecurityContextHolder 에 저장할 Authentication 객체 생성
    public Authentication getAuthentication(String Jwt) {
        UserDetails userDetails = memberDetailsService.loadUserByUsername(this.getUsernameFromJwt(Jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰의 유효성 검사
    // Jwts 에서 알아서 처리해준다.
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }*/
}
