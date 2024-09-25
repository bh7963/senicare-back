package com.phu.senicare.dto.response.nurse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.phu.senicare.dto.response.ResponseCode;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.ResponseMessage;
import com.phu.senicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class GetNurseResponseDto extends ResponseDto {
    
    private String userId;
    private String name;
    private String telNumber;

    private GetNurseResponseDto(NurseEntity nurseEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = nurseEntity.getUserId();
        this.name = nurseEntity.getName();
        this.telNumber = nurseEntity.getTelNumber();
    }
    
    public static ResponseEntity<GetNurseResponseDto> success(NurseEntity nurseEntity) {
        GetNurseResponseDto responseBody = new GetNurseResponseDto(nurseEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
