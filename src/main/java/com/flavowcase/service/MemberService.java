package com.flavowcase.service;

import com.flavowcase.domain.Member;
import com.flavowcase.domain.MemberRepository;
import com.flavowcase.dto.KakaoLoginResponse;
import com.flavowcase.dto.kakao.KakaoInfoResponse;
import com.flavowcase.dto.kakao.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${authorization.header}")
    private String AUTHORIZATION_HEADER;

    /*
    카카오 로그인/회원가입
     */
    public KakaoLoginResponse kakaoSignin(String code) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("redirect_uri", REDIRECT_URI);
        requestBody.add("code", code);
        requestBody.add("client_secret", CLIENT_SECRET);

        Flux<KakaoTokenResponse> response = webClient.post()
                .uri(KAKAO_URI_TOKEN)
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToFlux(KakaoTokenResponse.class);

        KakaoTokenResponse tokenResponse = response.blockFirst();
        String token = Objects.requireNonNull(tokenResponse).getAccess_token();
        KakaoInfoResponse memberInfo = getMemberInfo(token);

        // 카카오 ID로 회원가입 유무 조회 후 미가입 회원이면 서비스 회원가입 진행
        if (!memberRepository.existsById(Objects.requireNonNull(memberInfo).getId())) {
            Member member = Member.builder()
                    .id(memberInfo.getId())
                    .nickname(memberInfo.getKakao_account().getProfile().getNickname())
                    .build();
            memberRepository.save(member);
        }

        return KakaoLoginResponse.builder()
                .kakao_token(tokenResponse)
                .kakao_account(memberInfo.getKakao_account())
                .build();
    }

    /*
    카카오 회원정보 조회
     */
    public KakaoInfoResponse getMemberInfo(String token) {
        String kakaoToken = AUTHORIZATION_HEADER + token;

        Flux<KakaoInfoResponse> response = webClient.post()
                .uri(KAKAO_URI_INFO)
                .header("Authorization", kakaoToken)
                .contentType(APPLICATION_FORM_URLENCODED)
                .retrieve()
                .bodyToFlux(KakaoInfoResponse.class);

        return response.blockFirst();
    }
}