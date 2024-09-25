package com.phu.senicare.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.phu.senicare.dto.response.ResponseCode;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.ResponseMessage;
import com.phu.senicare.repository.resultSet.GetCustomerResultSet;

import lombok.Getter;

@Getter
public class GetCustomerResponseDto extends ResponseDto {
    
    private Integer customerNumer;
    private String profileImage;
    private String name;
    private String birth;
    private String chargerName;
    private String chargerId;
    private String address;

    private GetCustomerResponseDto (GetCustomerResultSet resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.customerNumer = resultSets.getCustomerNumber();
        this.profileImage = resultSets.getProfileImage();
        this.name = resultSets.getName();
        this.birth = resultSets.getBirth();
        this.chargerName = resultSets.getChargerName();
        this.chargerId = resultSets.getChargerId();
        this.address = resultSets.getAddress(); 
    }

    public static ResponseEntity<GetCustomerResponseDto> success(GetCustomerResultSet resultSets) {
        GetCustomerResponseDto responseBody = new GetCustomerResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
