package com.phu.senicare.dto.response.nurse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.phu.senicare.common.object.Nurse;
import com.phu.senicare.dto.response.ResponseCode;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.ResponseMessage;
import com.phu.senicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class GetNurseListResponseDto extends ResponseDto{
    
    private List<Nurse> nurses;

    private GetNurseListResponseDto(List<NurseEntity> nurseEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.nurses = Nurse.getList(nurseEntities);
    }

    public static ResponseEntity<GetNurseListResponseDto> success(List<NurseEntity> nurseEntities) {
        GetNurseListResponseDto responseBody = new GetNurseListResponseDto(nurseEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
