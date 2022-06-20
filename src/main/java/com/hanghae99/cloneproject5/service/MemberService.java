package com.hanghae99.cloneproject5.service;

import com.hanghae99.cloneproject5.dto.SignupDto;
import com.hanghae99.cloneproject5.model.Member;
import com.hanghae99.cloneproject5.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    // 회원가입시 입력된 정보 유효성 검사
    @Transactional
    public void userSignup(SignupDto signupDto){

        dubCheckUsername(signupDto.getUsername());

        dupCheckEmail(signupDto.getEmail());

        sameCheckPassword(signupDto.getPassword(), signupDto.getPasswordCheck());

        // password encoding
        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        // 위의 모든 검사가 예외 발생 없이 통과됐을 경우 저장
        Member member = new Member(signupDto);
        memberRepository.save(member);
    }


    // Username 중복 체크 메소드
    public void dubCheckUsername(String param){
        if(memberRepository.findByUsername(param).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 아이디");
        }

    }

    // email 중복 체크 메소드
    public void dupCheckEmail(String param){
        if(memberRepository.findByEmail(param).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일");
        }
    }

    // password 일치 체크 메소드

    public void sameCheckPassword(String param1, String param2){
        if(!param1.equals(param2)) { throw new IllegalArgumentException("비밀번호 불일치"); }
    }

    // 나중에 해야할 일
    // - 에러 메세지 보내는 법 찾기

}
