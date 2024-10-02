package com.phu.senicare.dto.response.nurse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.phu.senicare.common.object.ChargedCustomer;
import com.phu.senicare.dto.response.ResponseCode;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.ResponseMessage;
import com.phu.senicare.entity.CustomerEntity;

import lombok.Getter;

@Getter
public class GetChargedCustomerResponseDto extends ResponseDto {
    
    private List<ChargedCustomer> customers;

    private GetChargedCustomerResponseDto (List<CustomerEntity> customerEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.customers = ChargedCustomer.getList(customerEntities);
        
    }

    public static ResponseEntity<? super GetChargedCustomerResponseDto> success(List<CustomerEntity> customerEntities) {
        GetChargedCustomerResponseDto responseBody = new GetChargedCustomerResponseDto(customerEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
