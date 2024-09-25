package com.phu.senicare.dto.request.customer;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCustomerRequestDto {
    
    @NotBlank
    private String profileImage;
    @NotBlank
    private String name;
    @NotBlank
    private String birth;
    @NotBlank
    private String charger;
    @NotBlank
    private String address;
    @NotBlank
    private String location;


}
