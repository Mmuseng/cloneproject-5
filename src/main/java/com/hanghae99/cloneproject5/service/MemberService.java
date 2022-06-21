package com.hanghae99.cloneproject5.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hanghae99.cloneproject5.dto.*;
import com.hanghae99.cloneproject5.jwt.SHA256;
import com.hanghae99.cloneproject5.model.Member;
import com.hanghae99.cloneproject5.model.RefreshToken;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import com.hanghae99.cloneproject5.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateRefreshToken(Date now, Date expireDate) {
        String token = "";

        try {
            Algorithm algorithm = Algorithm.HMAC256("fhrmdlsdlek");
            token = JWT.create()
                    .withIssuer("hanghae99")
                    .withIssuedAt(now)
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return "";
        }

        return token;
    }

    public MemberLoginRespDto generateToken(Long id, String username) {
        String token = "";
        Date now = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + (1000L * 3600L * 24L * 7L)); //7일
        Date refreshExpireDate = new Date(System.currentTimeMillis() + (1000L * 3600L * 24L * 30L)); //30일

        String refreshToken = generateRefreshToken(now, refreshExpireDate);

        if (refreshToken.equals(""))
            return new MemberLoginRespDto(false, "리프레시 토큰 생성에 실패 하였습니다.");

        try {
            Algorithm algorithm = Algorithm.HMAC256("fhrmdlsdlek");
            token = JWT.create()
                    .withIssuer("hanghae99")
                    .withIssuedAt(now)
                    .withExpiresAt(expireDate)
                    .withClaim("id", id)
                    .withClaim("username", username)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return new MemberLoginRespDto(false, "토큰 생성에 실패 하였습니다.");
        }

        refreshTokenRepository.save(new RefreshToken(token, refreshToken));

        return new MemberLoginRespDto(true, token, refreshToken, "로그인 성공");
    }

    public MemberLoginRespDto verifyRefreshToken(String accessToken, String refreshToken) {
        DecodedJWT refresh_decode = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256("fhrmdlsdlek");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("hanghae99")
                    .build();

            refresh_decode = verifier.verify(refreshToken);
        } catch (JWTCreationException exception) {
            return new MemberLoginRespDto(false, "리프레시 토큰이 유효하지 않습니다.");
        }

        DecodedJWT access_decode;

        try {
            access_decode = JWT.decode(accessToken);
        } catch (JWTVerificationException exception) {
            return new MemberLoginRespDto(false, "액세스 토큰이 유효하지 않습니다.");
        }

        Optional<RefreshToken> refreshToken0pt = refreshTokenRepository.findByAccessTokenAndRefreshToken(accessToken, refreshToken);

        if (!refreshToken0pt.isPresent()) {
            return new MemberLoginRespDto(false, "토큰 재발급 실패");
        }

        Long id = Long.parseLong(access_decode.getClaim("id").toString());
        String username = access_decode.getClaim("username").toString();

        return this.generateToken(id, username);
    }

    public MemberTokenRespDto tokenTest(String token) {
        DecodedJWT jwt;

        try {
            Algorithm algorithm = Algorithm.HMAC256("fhrmdlsdlek");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("hanghae99")
                    .build();

            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            return new MemberTokenRespDto(false, "토큰이 유효하지 않습니다.");
        }

        return new MemberTokenRespDto(true, jwt.getPayload(), "토큰 해석 성공");
    }

    public MemberLoginRespDto loginUser(SignInDto Dto) throws NoSuchAlgorithmException {
        Boolean result = true;
        String errMsg = "로그인 성공";

        String username = Dto.getUsername();
        String password = Dto.getPassword();
        String pwSHA256 = SHA256.encrypt(password);

        Optional<Member> member = memberRepository.findByUsernameAndPassword(username, pwSHA256);

        if(!member.isPresent()) {
            errMsg = "아이디 또는 비밀번호가 틀렸습니다.";
            result = false;
            return new MemberLoginRespDto(result, errMsg);
        }

        return this.generateToken(member.get().getId(), member.get().getUsername());
    }

    public MemberRegisterRespDto registerUser(SignUpDto Dto) throws NoSuchAlgorithmException {
        Boolean result = true;
        String err_msg = "회원가입 성공";
        String email = Dto.getEmail();
        String githubId = Dto.getGithubId();
        String username = Dto.getUsername();
        String introduce = Dto.getIntroduce();

        Optional<Member> foundEmail = memberRepository.findByEmail(email);
        Optional<Member> foundUsername = memberRepository.findByUsername(username);

        // 회원 Email 중복 확인
        if (foundEmail.isPresent()) {
            err_msg = "중복된 사용자 ID가 존재합니다.";
            result = false;
            return new MemberRegisterRespDto(result, err_msg);
        }

        // 회원 닉네임 중복 확인
        if (foundUsername.isPresent()) {
            err_msg = "중복된 닉네임이 존재합니다.";
            result = false;
            return new MemberRegisterRespDto(result, err_msg);
        }

        // 패스워드 암호화
        String password = SHA256.encrypt(Dto.getPassword());

        Member member = new Member(email, githubId, username, introduce, password);
        System.out.println(Dto.getEmail());
        System.out.println(Dto.getUsername());
        memberRepository.save(member);

        MemberRegisterRespDto responseDto = new MemberRegisterRespDto(result, err_msg);
        return responseDto;
    }

    // username 중복확인 버튼
    public Boolean checkNameDuplicate(String username) {
        Member member = memberRepository.findByUsername(username).orElse(null);

        try {
            if (member.getUsername().equals(username)) {
                return false;
            }
        } catch (NullPointerException e) {
            return true;
        }
        return true;
    }
}
