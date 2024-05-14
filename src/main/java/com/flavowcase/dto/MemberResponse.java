package com.flavowcase.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponse {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

}
