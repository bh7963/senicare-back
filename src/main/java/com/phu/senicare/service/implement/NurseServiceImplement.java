package com.phu.senicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.nurse.GetNurseListResponseDto;
import com.phu.senicare.dto.response.nurse.GetNurseResponseDto;
import com.phu.senicare.dto.response.nurse.GetSignInResponseDto;
import com.phu.senicare.entity.NurseEntity;
import com.phu.senicare.repository.NurseRepository;
import com.phu.senicare.service.NurseService;

import java.util.List;
import java.util.ArrayList;

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

    @Override
    public ResponseEntity<? super GetNurseListResponseDto> getNurseList() {
        
        List<NurseEntity> nurseEntities = new ArrayList<>();

        try {
            nurseEntities = nurseRepository.findAll();

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNurseListResponseDto.success(nurseEntities);
    }

    @Override
    public ResponseEntity<? super GetNurseResponseDto> getNurse(String userId) {
        NurseEntity nurseEntity = null;

        try {
            nurseEntity = nurseRepository.findByUserId(userId);
            if(nurseEntity == null) return ResponseDto.noExistUserId();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNurseResponseDto.success(nurseEntity);
    }
    

}
