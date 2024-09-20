package com.phu.senicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phu.senicare.dto.request.tool.PatchToolRequestDto;
import com.phu.senicare.dto.request.tool.PostToolRequestDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.tool.GetToolListResponseDto;
import com.phu.senicare.dto.response.tool.GetToolResponseDto;
import com.phu.senicare.service.ToolService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tool")
@RequiredArgsConstructor
public class ToolController {
    
    private final ToolService toolService;

    @PostMapping(value={"", "/"})
    public ResponseEntity<ResponseDto> postTool(
        @RequestBody @Valid PostToolRequestDto reqeustBody
    ){
        ResponseEntity<ResponseDto> response = toolService.postTool(reqeustBody);
        return response;
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetToolListResponseDto> getToolKist(){
        ResponseEntity<? super GetToolListResponseDto> response = toolService.getToolList();
        return response;
    }

    @GetMapping("/{toolNumber}")
    public ResponseEntity<? super GetToolResponseDto> getTool(
        @PathVariable("toolNumber") Integer toolNumber
    ){
        ResponseEntity<? super GetToolResponseDto> response = toolService.getTool(toolNumber);
        return response;
    }

    @PatchMapping("/{toolNumber}")
    public ResponseEntity<ResponseDto> patchTool(
        @PathVariable("toolNumber") Integer toolNumber,
        @RequestBody @Valid PatchToolRequestDto requsetBody
    ) {
        ResponseEntity<ResponseDto> response = toolService.patchTool(toolNumber, requsetBody);
        return response;
    }

    @DeleteMapping("/{toolNumber}")
    public ResponseEntity<ResponseDto> deleteTool(
        @PathVariable("toolNumber") Integer toolNumber
    ) {
        ResponseEntity<ResponseDto> response = toolService.deleteTool(toolNumber);
        return response;
    }

}
