package com.phu.senicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phu.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.phu.senicare.dto.request.customer.PostCareRecordRequestDto;
import com.phu.senicare.dto.request.customer.PostCustomerRequestDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.service.CustomerService;
import com.phu.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.phu.senicare.dto.response.customer.GetCustomerResponseDto;
import com.phu.senicare.dto.response.customer.GetCareRecordListResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    
    @PostMapping(value={"", "/"})
    public ResponseEntity<ResponseDto> postCustomer(
        @RequestBody @Valid PostCustomerRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = customerService.postCustomer(requestBody);
        return response;
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetCustomerListResponseDto> getListCustomer () {
        ResponseEntity<? super GetCustomerListResponseDto> response = customerService.getCustomerList();
        return response;
    }

    @GetMapping(value={"/{customerNumber}"})
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(
        @PathVariable("customerNumber") Integer customerNumber
    ) {
        ResponseEntity<? super GetCustomerResponseDto> respsonse = customerService.getCustomer(customerNumber);
        return respsonse;
    }

    @PatchMapping("/{customerNumber}")
    public ResponseEntity<ResponseDto> patchCustomer(
        @RequestBody @Valid PatchCustomerRequestDto requestBody, 
        @PathVariable("customerNumber") Integer customerNumber, 
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = customerService.patchCustomer(requestBody, customerNumber, userId);
        return response;
    }

    @DeleteMapping("/{customerNumber}")
    public ResponseEntity<ResponseDto> deleteCustomer(
        @PathVariable("customerNumber") Integer customerNumber, 
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = customerService.deleteCustomer(customerNumber, userId);
        return response;
    }

    @PostMapping("/{customerNumber}/care-record")
    public ResponseEntity<ResponseDto> postCareRecord(
        @RequestBody @Valid PostCareRecordRequestDto requestBody, 
        @PathVariable("customerNumber") Integer customerNumber,
        @AuthenticationPrincipal String userId
        ) {
            Integer usedToolNumber = requestBody.getUsedToolNumber();
            Integer count = requestBody.getCount();
            if (
                (usedToolNumber !=null && count == null)||
                (usedToolNumber == null && count != null)
            ) return ResponseDto.validationFaile();

            ResponseEntity<ResponseDto> response = customerService.postCareRecord(requestBody, customerNumber, userId);
            return response;
        }

        @GetMapping("/{customerNumber}/care-records")
        public ResponseEntity<? super GetCareRecordListResponseDto> getCareRecordList(
            @PathVariable("customerNumber") Integer customerNumber
        ) {
            ResponseEntity<? super GetCareRecordListResponseDto> response = customerService.getCareRecord(customerNumber);
            return response;
        }
}
