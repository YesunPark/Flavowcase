package com.flavowcase.controller;

import com.flavowcase.dto.SigninResponse;
import com.flavowcase.dto.SignupRequest;
import com.flavowcase.dto.SigninRequest;
import com.flavowcase.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /*
    회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request) {
        memberService.signup(request);
        return ResponseEntity.status(CREATED).build();
    }

    /*
    로그인
     */
    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@Valid @RequestBody SigninRequest request) {
        SigninResponse response = memberService.signin(request);
        return ResponseEntity.ok(response);
    }

}
