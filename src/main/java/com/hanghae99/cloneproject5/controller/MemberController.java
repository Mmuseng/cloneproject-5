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

    @GetMapping("/user/tokentest")
    public MemberTokenRespDto tokenTest(@RequestHeader("Authorization") String authorization) {
        if (!authorization.startsWith("Bearer "))
            return new MemberTokenRespDto(false, "토큰 인증 방식이 Bearer 가 아닙니다.");

        String token = authorization.substring(7, authorization.length() - 1);

        return memberService.tokenTest(token);
    }

    @PostMapping("/user/refresh")
    public MemberLoginRespDto tokenRefresh(@RequestBody RefreshTokenDto Dto) {

        return memberService.verifyRefreshToken(Dto.getAccessToken(), Dto.getRefreshToken());
    }

    @GetMapping("/user/login")
    public String login() {

        return "login";
    }

    @PostMapping("/user/login")
    public MemberLoginRespDto login(@RequestBody SignInDto Dto) throws NoSuchAlgorithmException {

        return memberService.loginUser(Dto);
    }

    @GetMapping("/user/signup")
    public String signup() {

        return "signup";
    }

    @PostMapping("/user/signup")
    public MemberRegisterRespDto registerMember(@RequestBody SignUpDto Dto) throws NoSuchAlgorithmException {

        return memberService.registerUser(Dto);
    }

    @ResponseBody
    @GetMapping("/user/nameDupCheck/{username}")
    public Boolean nameDupCheck(@PathVariable String username) {

        return memberService.checkNameDuplicate(username);
    }
}
