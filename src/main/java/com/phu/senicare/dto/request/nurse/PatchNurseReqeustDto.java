package com.phu.senicare.dto.request.nurse;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchNurseReqeustDto {
    
    @NotBlank
    private String name;
}
