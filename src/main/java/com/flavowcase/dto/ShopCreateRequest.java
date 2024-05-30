package com.flavowcase.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ShopCreateRequest {
    @NotNull(message = "매장명을 입력해주세요!")
    private String name;

    private String address;

    private String thumbnail; // 이미지 저장 어떻게 하는지 알아봐야 함.

    private String contactNumber;

    private String description;

    private String notice;
}
