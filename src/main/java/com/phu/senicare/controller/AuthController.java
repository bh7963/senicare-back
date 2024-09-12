package com.phu.senicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phu.senicare.dto.request.auth.IdCheckRequestDto;
import com.phu.senicare.dto.request.auth.TelAuthCheckRequestDto;
import com.phu.senicare.dto.request.auth.TelAuthRequestDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto requsetBody
    ){
        ResponseEntity<ResponseDto> response = authService.idCheck(requsetBody);
        return response;
    }

    @PostMapping("/tel-auth")
    public ResponseEntity<ResponseDto> telAuth(
        @RequestBody @Valid TelAuthRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.telAuth(requestBody);
        return response;
    }

    @PostMapping("/tel-auth-check")
    public ResponseEntity<ResponseDto> telAuthCheck(
        @RequestBody @Valid TelAuthCheckRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = authService.telAuthCheck(requestBody);
        return response; 
    }

}
