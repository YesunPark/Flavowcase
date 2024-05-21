package com.flavowcase.controller;

import com.flavowcase.dto.kakao.KakaoInfoResponse;
import com.flavowcase.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    /*
//    회원가입
//     */
//    @PostMapping("/signup")
//    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request) {
//        memberService.signup(request);
//        return ResponseEntity.status(CREATED).build();
//    }
//
//    /*
//    로그인
//     */
//    @PostMapping("/signin")
//    public ResponseEntity<SigninResponse> signin(@Valid @RequestBody SigninRequest request) {
//        SigninResponse response = memberService.signin(request);
//        return ResponseEntity.ok(response);
//    }

    /*
    로그인
     */
    @GetMapping("/kakao")
    public ResponseEntity<KakaoInfoResponse>  kakaoSignin(@RequestParam String code) {
        KakaoInfoResponse response = memberService.kakaoSignin(code);
        return ResponseEntity.ok(response);
    }
}
