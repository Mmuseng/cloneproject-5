package com.hanghae99.cloneproject5.controller;

import com.hanghae99.cloneproject5.dto.SignupDto;
import com.hanghae99.cloneproject5.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    // 테스트 시 리턴값 수정
    @PostMapping("/signup")
    public void userSignUp(@RequestBody SignupDto signupDto){
        memberService.userSignup(signupDto);
    }
}
