package com.phu.senicare.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp="^[0-9]{11}")
    private String telNumber;
    @NotBlank
    private String authNumber;

    
}
