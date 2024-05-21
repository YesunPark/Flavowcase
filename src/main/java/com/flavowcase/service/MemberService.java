package com.flavowcase.service;

import com.flavowcase.domain.Member;
import com.flavowcase.domain.MemberRepository;
import com.flavowcase.dto.kakao.KakaoInfoResponse;
import com.flavowcase.dto.kakao.KakaoLoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Slf4j
@RequiredArgsConstructor
@Service
@Component
public class MemberService {

    private final WebClient webClient;
    private final MemberRepository memberRepository;

    @Value("${kakao.client.id}")
    private String CLIENT_ID;

    @Value("${kakao.client.secret}")
    private String CLIENT_SECRET;

    @Value("${kakao.redirect.uri}")
    private String REDIRECT_URI;

    @Value("${kakao.uri.token}")
    private String KAKAO_URI_TOKEN;

    @Value("${kakao.uri.info}")
    private String KAKAO_URI_INFO;


//    /*
//    회원가입
//     */
//    public void signup(SignupRequest request) {
//        if (memberRepository.existsByEmail(request.getEmail())) { // 이메일 중복 시 예외처리
//            throw new CustomException(EMAIL_ALREADY_EXISTS);
//        }
//        memberRepository.save(SignupRequest.toEntity(request));
//    }

    /*
    카카오 로그인/회원가입
     */
    public KakaoInfoResponse kakaoSignin(String code) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("redirect_uri", REDIRECT_URI);
        requestBody.add("code", code);
        requestBody.add("client_secret", CLIENT_SECRET);

        Flux<KakaoLoginResponse> response = webClient.post()
                .uri(KAKAO_URI_TOKEN)
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToFlux(KakaoLoginResponse.class);

        String token = Objects.requireNonNull(response.blockFirst()).getAccess_token();

        KakaoInfoResponse memberInfo = getMemberInfo(token);

        // 카카오 ID로 회원가입 유무 조회 후 미가입 회원이면 서비스 회원가입 진행
        if (!memberRepository.existsById(Objects.requireNonNull(memberInfo).getId())) {
            Member member = Member.builder()
                    .id(memberInfo.getId())
                    .nickname(memberInfo.getKakao_account().getProfile().getNickname())
                    .build();
            memberRepository.save(member);
        }

        return memberInfo;
    }

    /*
    카카오 회원정보 조회
     */
    public KakaoInfoResponse getMemberInfo(String token) {
        String kakaoToken = "Bearer " + token;

        Flux<KakaoInfoResponse> response = webClient.post()
                .uri(KAKAO_URI_INFO)
                .header("Authorization", kakaoToken)
                .contentType(APPLICATION_FORM_URLENCODED)
                .retrieve()
                .bodyToFlux(KakaoInfoResponse.class);

        return response.blockFirst();
    }
}