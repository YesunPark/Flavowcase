package com.flavowcase.service;

import com.flavowcase.common.CustomException;
import com.flavowcase.common.ErrorCode;
import com.flavowcase.domain.MemberRepository;
import com.flavowcase.dto.SigninRequest;
import com.flavowcase.dto.SigninResponse;
import com.flavowcase.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.flavowcase.common.ErrorCode.EMAIL_ALREADY_EXISTS;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    회원가입
     */
    public void signup(SignupRequest request) {
        if(memberRepository.existsByEmail(request.getEmail())){ // 이메일 중복 시 예외처리
            throw new CustomException(EMAIL_ALREADY_EXISTS);
        }
        memberRepository.save(SignupRequest.toEntity(request));
    }

    /*
    로그인
     */
    public SigninResponse signin(SigninRequest request) {
        return null;
    }
}
