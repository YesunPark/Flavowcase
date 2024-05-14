package com.flavowcase.dto;

import com.flavowcase.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    public static Member toEntity(MemberRequest request) {
        return Member.builder()
                .email(request.email)
                .password(request.password)
                .name(request.name)
                .phone(request.phone)
                .build();
    }
}
