package com.phu.senicare.dto.response.customer;

import com.phu.senicare.common.object.CareRecord;
import com.phu.senicare.dto.response.ResponseCode;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.ResponseMessage;
import com.phu.senicare.entity.CareRecordEntity;

import lombok.Getter;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetCareRecordListResponseDto extends ResponseDto{

    private List<CareRecord> careRecords;

    private GetCareRecordListResponseDto(List<CareRecordEntity> careRecordEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.careRecords = CareRecord.getList(careRecordEntities);
    }

    public static ResponseEntity<GetCareRecordListResponseDto> success(List<CareRecordEntity> careRecordEntities) {
        GetCareRecordListResponseDto responseBody = new GetCareRecordListResponseDto(careRecordEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    
}
