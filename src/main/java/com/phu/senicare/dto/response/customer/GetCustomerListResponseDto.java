package com.phu.senicare.dto.response.customer;

import com.phu.senicare.common.object.Customer;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.ResponseMessage;
import com.phu.senicare.repository.resultSet.GetCustomersResultSet;
import com.phu.senicare.dto.response.ResponseCode;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetCustomerListResponseDto extends ResponseDto{
    
    private List<Customer> customers;

    private GetCustomerListResponseDto(List<GetCustomersResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.customers = Customer.getList(resultSets);
    }

    public static ResponseEntity<GetCustomerListResponseDto> success(List<GetCustomersResultSet> resultSets) {
        GetCustomerListResponseDto responseBody = new GetCustomerListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    
}
