package com.flavowcase.dto;

import com.flavowcase.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {

    @NotNull(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;

    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotNull(message = "이름을 입력해주세요.")
    private String name;

    @Pattern(regexp="^01(?:0|1|[6-9])\\d{7,8}$", message="전화번호를 확인해주세요!")
    @NotNull(message = "전화번호를 입력해주세요.")
    private String phone;

    public static Member toEntity(SignupRequest request) {
        return Member.builder()
                .email(request.email)
                .password(request.password)
                .name(request.name)
                .phone(request.phone)
                .build();
    }
}
