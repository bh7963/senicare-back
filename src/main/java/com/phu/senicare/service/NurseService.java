package com.phu.senicare.service;

import org.springframework.http.ResponseEntity;

import com.phu.senicare.dto.response.nurse.GetSignInResponseDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.phu.senicare.dto.response.nurse.GetNurseResponseDto;
import com.phu.senicare.dto.request.nurse.PatchNurseReqeustDto;
import com.phu.senicare.dto.response.nurse.GetChargedCustomerResponseDto;

public interface NurseService {
    
    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
    ResponseEntity<? super GetNurseResponseDto> getNurse(String userId);
    ResponseEntity<? super GetNurseListResponseDto> getNurseList();
    ResponseEntity<ResponseDto> patchNurse(PatchNurseReqeustDto dto, String userId);
    ResponseEntity<? super GetChargedCustomerResponseDto> getCharedCustomerList(String nurseId);

}
