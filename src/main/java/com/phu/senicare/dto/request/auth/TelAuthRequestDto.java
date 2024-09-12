package com.phu.senicare.dto.request.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 전화번호 인증하는 Request Body에 대한 DTO

@Getter
@Setter
@NoArgsConstructor
public class TelAuthRequestDto {
    
    @NotBlank
    @Pattern(regexp="^[0-9]{11}")
    private String telNumber;
}
