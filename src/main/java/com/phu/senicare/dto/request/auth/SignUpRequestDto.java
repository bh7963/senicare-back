package com.phu.senicare.dto.request.auth;

import org.hibernate.validator.constraints.Length;

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
    @Length(max=5)
    private String name;
    @NotBlank
    @Length(max=20)
    private String userId;
    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{8,13}$")
    private String password;
    @NotBlank
    @Pattern(regexp="^[0-9]{11}$")
    private String telNumber;
    @NotBlank
    private String authNumber;
    @NotBlank
    @Pattern(regexp="^(HOME|KAKAO|NAVER)$")
    private String joinPath;
    private String snsId;

    
}
