package com.flavowcase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SigninResponse {

    private String kakaoAuthCode;
}
