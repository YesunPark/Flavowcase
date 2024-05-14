package com.flavowcase.service;

import com.flavowcase.domain.MemberRepository;
import com.flavowcase.dto.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    회원가입
     */
    public void signup(MemberRequest member) {
        memberRepository.save(MemberRequest.toEntity(member));
    }
}
