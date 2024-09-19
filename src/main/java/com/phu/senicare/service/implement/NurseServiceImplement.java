package com.phu.senicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.nurse.GetSignInResponseDto;
import com.phu.senicare.entity.NurseEntity;
import com.phu.senicare.repository.NurseRepository;
import com.phu.senicare.service.NurseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NurseServiceImplement implements NurseService{

    private final NurseRepository nurseRepository;

    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        NurseEntity nurseEntity = null;
        try {
            
            nurseEntity = nurseRepository.findByUserId(userId);
            if(nurseEntity == null) return ResponseDto.noExistUserId();
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSignInResponseDto.success(nurseEntity);
    }
    

}
