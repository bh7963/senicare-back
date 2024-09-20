package com.phu.senicare.dto.response.tool;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.phu.senicare.common.object.Tool;
import com.phu.senicare.dto.response.ResponseCode;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.ResponseMessage;
import com.phu.senicare.entity.ToolEntity;

import lombok.Getter;
@Getter
public class GetToolListResponseDto  extends ResponseDto{
    
    private List<Tool> tools;

    private GetToolListResponseDto(List<ToolEntity> toolEntities) {
        super(ResponseCode.SUCCESS,ResponseMessage.SUCCESS);
        this.tools = Tool.getList(toolEntities);
    }

    public static ResponseEntity<GetToolListResponseDto> success(List<ToolEntity> toolEntities){
        GetToolListResponseDto responseBody = new GetToolListResponseDto(toolEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
