package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.dto.requestDto.RefreshTokenDto;
import com.hanghae99.cloneproject5.dto.requestDto.SignInDto;
import com.hanghae99.cloneproject5.dto.requestDto.SignUpDto;
import com.hanghae99.cloneproject5.dto.responseDto.MemberLoginRespDto;
import com.hanghae99.cloneproject5.dto.responseDto.MemberRegisterRespDto;
import com.hanghae99.cloneproject5.dto.responseDto.MemberTokenRespDto;
import com.hanghae99.cloneproject5.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    // 토큰 테스트
    @GetMapping("/user/tokentest")
    public MemberTokenRespDto tokenTest(@RequestHeader("Authorization") String authorization) {
        if (!authorization.startsWith("Bearer "))
            return new MemberTokenRespDto(false, "토큰 인증 방식이 Bearer 가 아닙니다.");

        String token = authorization.substring(7, authorization.length() - 1);

        return memberService.tokenTest(token);
    }

    // 토큰 재발급
    @PostMapping("/user/refresh")
    public MemberLoginRespDto tokenRefresh(@RequestBody RefreshTokenDto Dto) {

        return memberService.verifyRefreshToken(Dto.getAccessToken(), Dto.getRefreshToken());
    }

    // 유저 로그인 페이지
    @GetMapping("/user/login")
    public String login() {

        return "login";
    }

    // 로그인 요청 처리
    @PostMapping("/user/login")
    public MemberLoginRespDto login(@RequestBody SignInDto Dto) throws NoSuchAlgorithmException {

        return memberService.loginUser(Dto);
    }

    // 유저 회원가입 페이지
    @GetMapping("/user/signup")
    public String signup() {

        return "signup";
    }

    // 회원가입 요청 처리
    @PostMapping("/user/signup")
    public MemberRegisterRespDto registerMember(@RequestBody SignUpDto Dto) throws NoSuchAlgorithmException {

        return memberService.registerUser(Dto);
    }

    // username 중복 확인
    @ResponseBody
    @GetMapping("/user/nameDupCheck/{username}")
    public Boolean nameDupCheck(@PathVariable String username) {

        return memberService.checkNameDuplicate(username);
    }
}
