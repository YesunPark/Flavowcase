package com.flavowcase.dto;

import com.flavowcase.dto.kakao.KakaoAccountResponse;
import com.flavowcase.dto.kakao.KakaoTokenResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoLoginResponse {

    private KakaoTokenResponse kakao_token;
    private KakaoAccountResponse kakao_account;
}
