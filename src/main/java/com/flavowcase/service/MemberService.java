package com.flavowcase.service;

import com.flavowcase.domain.Member;
import com.flavowcase.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    /*
    회원가입
     */
    public void signup(Member member) {
        memberRepository.save(member);
    }
}
