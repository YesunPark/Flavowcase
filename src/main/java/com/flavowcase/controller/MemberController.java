package com.flavowcase.controller;

import com.flavowcase.domain.Member;
import com.flavowcase.domain.MemberRepository;
import com.flavowcase.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public ResponseEntity<Void> signup(Member member) {

        memberService.signup(member);
        return ResponseEntity.ok().build();
    }
}
