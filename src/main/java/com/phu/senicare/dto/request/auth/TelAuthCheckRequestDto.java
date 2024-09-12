package com.phu.senicare.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TelAuthCheckRequestDto {

    @NotBlank
    private String telNumber;
    @NotBlank
    private String authNumber;
    
}
