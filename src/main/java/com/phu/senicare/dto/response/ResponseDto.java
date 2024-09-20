package com.phu.senicare.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Response의 공통적 형태(code, message)
@Getter
@AllArgsConstructor
public class ResponseDto{
    
    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> success(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFaile(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAILE, ResponseMessage.VALIDATION_FAILE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedUserId(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_USER_ID, ResponseMessage.DUPLICATE_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedTelNumber(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_TELNUMBER, ResponseMessage.DUPLICATE_TELNUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noExistUserId(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_USER_ID, ResponseMessage.NO_EXIST_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noEixstTool(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NO_EXIST_TOOL, ResponseMessage.NO_EXIST_TOOL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> telAuthFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.TEL_AUTH_FAILE, ResponseMessage.TEL_AUTH_FAILE);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    } 

    public static ResponseEntity<ResponseDto> messageSendFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.MESSAGE_SEND_FAILE, ResponseMessage.MESSAGE_SEND_FAILE);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> tokenCreateFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.TOKEN_CREATE_FAIL, ResponseMessage.TOKEN_CREATE_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
    
    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
