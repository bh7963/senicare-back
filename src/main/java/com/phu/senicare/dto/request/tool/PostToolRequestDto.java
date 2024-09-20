package com.phu.senicare.dto.request.tool;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostToolRequestDto {
    
    @NotBlank
    private String name;
    @NotBlank
    private String purpose;
    @NotNull
    private Integer count;

}
