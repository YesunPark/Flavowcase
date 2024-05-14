package com.flavowcase.controller;

import com.flavowcase.domain.Member;
import com.flavowcase.dto.MemberRequest;
import com.flavowcase.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody MemberRequest member) {

        memberService.signup(member);
        return ResponseEntity.ok().build();
    }
}
