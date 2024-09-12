package com.phu.senicare.service;

import org.springframework.http.ResponseEntity;

import com.phu.senicare.dto.request.auth.IdCheckRequestDto;
import com.phu.senicare.dto.request.auth.SignUpRequestDto;
import com.phu.senicare.dto.request.auth.TelAuthCheckRequestDto;
import com.phu.senicare.dto.request.auth.TelAuthRequestDto;
import com.phu.senicare.dto.response.ResponseDto;

public interface AuthService {
    
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto);

    ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto);

    ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);

    

}
