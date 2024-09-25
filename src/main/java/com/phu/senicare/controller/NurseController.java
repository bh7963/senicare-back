package com.phu.senicare.controller;
import com.phu.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.phu.senicare.dto.response.nurse.GetNurseResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phu.senicare.dto.response.nurse.GetSignInResponseDto;
import com.phu.senicare.service.NurseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/nurse")
@RequiredArgsConstructor
public class NurseController {
    
    private final NurseService nurseService;

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetNurseListResponseDto> getNurseList(){
        ResponseEntity<? super GetNurseListResponseDto> response = nurseService.getNurseList();
        return response;
    }

    @GetMapping("/sign-in")
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetSignInResponseDto> response = nurseService.getSignIn(userId);
        return response;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<? super GetNurseResponseDto> getNurse(
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetNurseResponseDto> response = nurseService.getNurse(userId);
        return response;
    }

}
