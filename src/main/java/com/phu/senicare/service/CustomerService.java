package com.phu.senicare.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.phu.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.phu.senicare.dto.request.customer.PostCareRecordRequestDto;
import com.phu.senicare.dto.request.customer.PostCustomerRequestDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.phu.senicare.dto.response.customer.GetCustomerResponseDto;
import com.phu.senicare.dto.response.customer.GetCareRecordListResponseDto;


public interface CustomerService {
    
    ResponseEntity<? super GetCustomerListResponseDto> getCustomerList ();
    ResponseEntity<ResponseDto> postCustomer( PostCustomerRequestDto dto );
    ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer customerNumber);
    ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, Integer customerNumber, String userId);
    ResponseEntity<ResponseDto> deleteCustomer(Integer customerNumber, String userId);

    ResponseEntity<ResponseDto> postCareRecord(PostCareRecordRequestDto dto, Integer customNumber, String userId);
    ResponseEntity<? super GetCareRecordListResponseDto > getCareRecord(Integer customerNumber);


}
