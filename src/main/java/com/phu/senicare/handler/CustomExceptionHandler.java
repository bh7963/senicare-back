package com.phu.senicare.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.phu.senicare.dto.response.ResponseDto;

// @RestControllerAdvice
// - 서비스 동작중의 특정한 예외사항을 REST API로 해결 하고 싶을 때 사용

// 예외 대처를 위한 REST API 처리
@RestControllerAdvice 
public class CustomExceptionHandler {
    
    // HttpMessageNotReadableException: Request Body가 없어서 읽지 못할 때
    // MethodArgumentNotValidException: 유효성 검사 실패
    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class
    })
    public ResponseEntity<ResponseDto> validExceptionHandler(Exception exception) {
        exception.printStackTrace();
        return ResponseDto.validationFaile();
    }
    

}
