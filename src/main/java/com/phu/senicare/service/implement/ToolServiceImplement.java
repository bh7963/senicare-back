package com.phu.senicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phu.senicare.common.object.Tool;
import com.phu.senicare.dto.request.tool.PatchToolRequestDto;
import com.phu.senicare.dto.request.tool.PostToolRequestDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.tool.GetToolListResponseDto;
import com.phu.senicare.dto.response.tool.GetToolResponseDto;
import com.phu.senicare.entity.ToolEntity;
import com.phu.senicare.repository.ToolRepository;
import com.phu.senicare.service.ToolService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolServiceImplement implements ToolService {
    
    private final ToolRepository toolRepository;
    

    @Override
    public ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto) {

        try {
            ToolEntity toolEntity = new ToolEntity(dto);
            toolRepository.save(toolEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetToolListResponseDto> getToolList() {

        List<ToolEntity> toolEntities = new ArrayList<>();

        try {

            toolEntities = toolRepository.findByOrderByToolNumberDesc();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetToolListResponseDto.success(toolEntities);
}

    @Override
    public ResponseEntity<? super GetToolResponseDto> getTool(Integer toolNumber) {
        ToolEntity toolEntity = null;
        try {

            toolEntity = toolRepository.findByToolNumber(toolNumber);
            if(toolEntity == null) return ResponseDto.noEixstTool();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetToolResponseDto.success(toolEntity);
    }

    @Override
    public ResponseEntity<ResponseDto> patchTool(Integer toolNumber, PatchToolRequestDto dto) {

        try {
            ToolEntity toolEntity = toolRepository.findByToolNumber(toolNumber);
            if(toolEntity==null) return ResponseDto.noEixstTool();
            
            toolEntity.patch(dto);
            
            toolRepository.save(toolEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteTool(Integer toolNumber) {

        try {
            ToolEntity toolEntity = toolRepository.findByToolNumber(toolNumber);
            if(toolEntity==null) return ResponseDto.noEixstTool();
            
            toolRepository.delete(toolEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    } 

}
